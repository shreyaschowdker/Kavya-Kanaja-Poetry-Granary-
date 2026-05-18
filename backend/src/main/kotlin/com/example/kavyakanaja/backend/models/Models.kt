package com.example.kavyakanaja.backend.models

import kotlinx.serialization.Serializable

@Serializable
data class Poet(
    val id: String,
    val name: String,
    val biography: String,
    val imageUrl: String? = null,
    val birthYear: Int? = null,
    val deathYear: Int? = null
)

@Serializable
data class WordMeaning(
    val word: String,
    val meaning: String,
    val context: String? = null
)

@Serializable
data class Poem(
    val id: String,
    val title: String,
    val poet: Poet,
    val content: String,
    val bhavartha: String,
    val audioUrl: String? = null,
    val difficultWords: List<WordMeaning> = emptyList(),
    val isPoemOfTheDay: Boolean = false
)
