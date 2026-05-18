package com.example.kavyakanaja.data.remote.dto

import com.example.kavyakanaja.data.local.entity.PoemEntity
import com.example.kavyakanaja.domain.model.Poem
import com.example.kavyakanaja.domain.model.Poet
import com.example.kavyakanaja.domain.model.WordMeaning
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
data class PoemDto(
    val id: String,
    val title: String,
    val poet: Poet,
    val content: String,
    val bhavartha: String,
    val audioUrl: String? = null,
    val difficultWords: List<WordMeaning> = emptyList(),
    val isPoemOfTheDay: Boolean
) {
    fun toPoemEntity(): PoemEntity {
        return PoemEntity(
            id = id,
            title = title,
            poetJson = Json.encodeToString(poet),
            content = content,
            bhavartha = bhavartha,
            audioUrl = audioUrl,
            difficultWordsJson = Json.encodeToString(difficultWords),
            isFavorite = false,
            isPoemOfTheDay = isPoemOfTheDay
        )
    }

    fun toPoem(): Poem {
        return Poem(
            id = id,
            title = title,
            poet = poet,
            content = content,
            bhavartha = bhavartha,
            audioUrl = audioUrl,
            difficultWords = difficultWords,
            isFavorite = false,
            isPoemOfTheDay = isPoemOfTheDay
        )
    }
}
