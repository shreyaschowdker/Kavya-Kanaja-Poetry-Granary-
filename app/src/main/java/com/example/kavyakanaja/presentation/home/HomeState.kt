package com.example.kavyakanaja.presentation.home

import com.example.kavyakanaja.domain.model.Poem

data class HomeState(
    val isLoading: Boolean = false,
    val poemOfTheDay: Poem? = null,
    val recentPoems: List<Poem> = emptyList(),
    val error: String = ""
)
