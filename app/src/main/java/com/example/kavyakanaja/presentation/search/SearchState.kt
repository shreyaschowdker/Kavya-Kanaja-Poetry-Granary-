package com.example.kavyakanaja.presentation.search

import com.example.kavyakanaja.domain.model.Poem

data class SearchState(
    val searchQuery: String = "",
    val isLoading: Boolean = false,
    val searchResults: List<Poem> = emptyList(),
    val error: String = ""
)
