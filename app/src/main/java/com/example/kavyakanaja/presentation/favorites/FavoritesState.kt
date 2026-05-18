package com.example.kavyakanaja.presentation.favorites

import com.example.kavyakanaja.domain.model.Poem

data class FavoritesState(
    val isLoading: Boolean = false,
    val favorites: List<Poem> = emptyList(),
    val error: String = ""
)
