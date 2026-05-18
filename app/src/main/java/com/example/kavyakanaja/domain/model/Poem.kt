package com.example.kavyakanaja.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Poem(
    val id: String,
    val title: String,
    val poet: Poet,
    val content: String,
    val bhavartha: String, // simple explanation
    val audioUrl: String? = null,
    val difficultWords: List<WordMeaning> = emptyList(),
    val isFavorite: Boolean = false,
    val isPoemOfTheDay: Boolean = false
)
