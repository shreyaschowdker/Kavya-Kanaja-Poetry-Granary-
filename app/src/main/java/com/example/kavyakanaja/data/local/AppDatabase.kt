package com.example.kavyakanaja.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.kavyakanaja.data.local.dao.PoemDao
import com.example.kavyakanaja.data.local.entity.PoemEntity

@Database(
    entities = [PoemEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract val poemDao: PoemDao

    companion object {
        const val DATABASE_NAME = "kavyakanaja_db"
    }
}
