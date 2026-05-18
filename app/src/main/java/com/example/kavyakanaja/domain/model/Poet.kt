package com.example.kavyakanaja.domain.model

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
