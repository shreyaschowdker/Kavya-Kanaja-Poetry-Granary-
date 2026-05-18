package com.example.kavyakanaja.presentation.poet

import com.example.kavyakanaja.domain.model.Poem
import com.example.kavyakanaja.domain.model.Poet

data class PoetState(
    val isLoading: Boolean = false,
    val poet: Poet? = null,
    val poems: List<Poem> = emptyList(),
    val error: String = ""
)
