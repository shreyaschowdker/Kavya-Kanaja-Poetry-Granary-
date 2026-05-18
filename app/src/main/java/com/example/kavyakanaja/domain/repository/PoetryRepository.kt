package com.example.kavyakanaja.domain.repository

import com.example.kavyakanaja.core.utils.Resource
import com.example.kavyakanaja.domain.model.Poem
import kotlinx.coroutines.flow.Flow

interface PoetryRepository {
    fun getPoems(): Flow<Resource<List<Poem>>>
    fun getPoemById(id: String): Flow<Resource<Poem>>
    fun getPoemOfTheDay(): Flow<Resource<Poem>>
    suspend fun toggleFavorite(poemId: String, isFavorite: Boolean)
    fun searchPoems(query: String): Flow<Resource<List<Poem>>>
    fun getFavoritePoems(): Flow<Resource<List<Poem>>>
    fun getPoemsByPoet(poetId: String): Flow<Resource<List<Poem>>>
    suspend fun loadPoemsFromAssets()
}
