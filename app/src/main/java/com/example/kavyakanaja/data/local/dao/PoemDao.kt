package com.example.kavyakanaja.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kavyakanaja.data.local.entity.PoemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PoemDao {
    @Query("SELECT * FROM poems")
    fun getAllPoems(): Flow<List<PoemEntity>>

    @Query("SELECT * FROM poems WHERE id = :id")
    fun getPoemById(id: String): Flow<PoemEntity?>

    @Query("SELECT * FROM poems WHERE isPoemOfTheDay = 1 LIMIT 1")
    fun getPoemOfTheDay(): Flow<PoemEntity?>

    @Query("SELECT * FROM poems WHERE isFavorite = 1")
    fun getFavoritePoems(): Flow<List<PoemEntity>>

    @Query("SELECT * FROM poems WHERE title LIKE '%' || :query || '%' OR poetJson LIKE '%' || :query || '%' OR content LIKE '%' || :query || '%'")
    fun searchPoems(query: String): Flow<List<PoemEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPoems(poems: List<PoemEntity>)

    @Query("UPDATE poems SET isFavorite = :isFavorite WHERE id = :poemId")
    suspend fun updateFavoriteStatus(poemId: String, isFavorite: Boolean)
}
