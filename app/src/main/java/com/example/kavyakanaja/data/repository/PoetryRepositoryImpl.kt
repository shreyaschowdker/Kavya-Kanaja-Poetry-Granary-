package com.example.kavyakanaja.data.repository

import android.content.Context
import com.example.kavyakanaja.core.utils.Resource
import com.example.kavyakanaja.data.local.dao.PoemDao
import com.example.kavyakanaja.data.remote.api.PoetryApi
import com.example.kavyakanaja.data.remote.dto.PoemDto
import com.example.kavyakanaja.domain.model.Poem
import com.example.kavyakanaja.domain.model.Poet
import com.example.kavyakanaja.domain.model.WordMeaning
import com.example.kavyakanaja.domain.repository.PoetryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import retrofit2.HttpException
import java.io.IOException

class PoetryRepositoryImpl(
    private val api: PoetryApi,
    private val dao: PoemDao,
    private val context: Context
) : PoetryRepository {

    override fun getPoems(): Flow<Resource<List<Poem>>> = flow {
        emit(Resource.Loading())
        try {
            val remotePoems = api.getPoems()
            dao.insertPoems(remotePoems.map { it.toPoemEntity() })
        } catch (e: HttpException) {
            emit(Resource.Error(message = "Oops, something went wrong!"))
        } catch (e: IOException) {
            emit(Resource.Error(message = "Couldn't reach server, check your internet connection."))
        }
        
        // Read from DB as single source of truth
        dao.getAllPoems().collect { entities ->
            emit(Resource.Success(entities.map { 
                val poet = parsePoet(it.poetJson)
                val words = parseDifficultWords(it.difficultWordsJson)
                it.toPoem(poet, words) 
            }))
        }
    }

    override fun getPoemById(id: String): Flow<Resource<Poem>> = flow {
        emit(Resource.Loading())
        dao.getPoemById(id).collect { entity ->
            if (entity != null) {
                val poet = parsePoet(entity.poetJson)
                val words = parseDifficultWords(entity.difficultWordsJson)
                emit(Resource.Success(entity.toPoem(poet, words)))
            } else {
                emit(Resource.Error("Poem not found"))
            }
        }
    }

    override fun getPoemOfTheDay(): Flow<Resource<Poem>> = flow {
        emit(Resource.Loading())
        dao.getPoemOfTheDay().collect { entity ->
            if (entity != null) {
                val poet = parsePoet(entity.poetJson)
                val words = parseDifficultWords(entity.difficultWordsJson)
                emit(Resource.Success(entity.toPoem(poet, words)))
            } else {
                emit(Resource.Error("Poem of the day not found"))
            }
        }
    }

    override suspend fun toggleFavorite(poemId: String, isFavorite: Boolean) {
        dao.updateFavoriteStatus(poemId, isFavorite)
    }

    override fun searchPoems(query: String): Flow<Resource<List<Poem>>> = flow {
        emit(Resource.Loading())
        dao.searchPoems(query).collect { entities ->
            emit(Resource.Success(entities.map { 
                val poet = parsePoet(it.poetJson)
                val words = parseDifficultWords(it.difficultWordsJson)
                it.toPoem(poet, words) 
            }))
        }
    }

    override fun getFavoritePoems(): Flow<Resource<List<Poem>>> = flow {
        emit(Resource.Loading())
        dao.getFavoritePoems().collect { entities ->
            emit(Resource.Success(entities.map { 
                val poet = parsePoet(it.poetJson)
                val words = parseDifficultWords(it.difficultWordsJson)
                it.toPoem(poet, words) 
            }))
        }
    }

    override fun getPoemsByPoet(poetId: String): Flow<Resource<List<Poem>>> = flow {
        emit(Resource.Loading())
        dao.getAllPoems().collect { entities ->
            val mapped = entities.map { 
                val poet = parsePoet(it.poetJson)
                val words = parseDifficultWords(it.difficultWordsJson)
                it.toPoem(poet, words) 
            }.filter { it.poet.id == poetId }
            emit(Resource.Success(mapped))
        }
    }

    override suspend fun loadPoemsFromAssets() {
        withContext(Dispatchers.IO) {
            try {
                val inputStream = context.assets.open("poems.json")
                val size = inputStream.available()
                val buffer = ByteArray(size)
                inputStream.read(buffer)
                inputStream.close()
                val jsonString = String(buffer, Charsets.UTF_8)
                
                val poems = Json.decodeFromString<List<PoemDto>>(jsonString)
                dao.insertPoems(poems.map { it.toPoemEntity() })
            } catch (e: Exception) {
                e.printStackTrace()
                // In production, we should handle this gracefully
            }
        }
    }

    private fun parsePoet(json: String): Poet {
        return try {
            Json.decodeFromString<Poet>(json)
        } catch (e: Exception) {
            Poet(id = "unknown", name = "Unknown", biography = "")
        }
    }

    private fun parseDifficultWords(json: String): List<WordMeaning> {
        return try {
            Json.decodeFromString<List<WordMeaning>>(json)
        } catch (e: Exception) {
            emptyList()
        }
    }
}
