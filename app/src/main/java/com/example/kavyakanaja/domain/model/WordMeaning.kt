package com.example.kavyakanaja.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class WordMeaning(
    val word: String,
    val meaning: String,
    val context: String? = null
)
