package com.example.kavyakanaja.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.kavyakanaja.domain.model.Poem
import com.example.kavyakanaja.domain.model.Poet
import com.example.kavyakanaja.domain.model.WordMeaning

@Entity(tableName = "poems")
data class PoemEntity(
    @PrimaryKey val id: String,
    val title: String,
    val poetJson: String,
    val content: String,
    val bhavartha: String,
    val audioUrl: String?,
    // For complex objects, Room needs a TypeConverter or we can store it as a JSON string
    val difficultWordsJson: String,
    val isFavorite: Boolean,
    val isPoemOfTheDay: Boolean
) {
    fun toPoem(poet: Poet, difficultWordsList: List<WordMeaning>): Poem {
        return Poem(
            id = id,
            title = title,
            poet = poet,
            content = content,
            bhavartha = bhavartha,
            audioUrl = audioUrl,
            difficultWords = difficultWordsList,
            isFavorite = isFavorite,
            isPoemOfTheDay = isPoemOfTheDay
        )
    }
}
