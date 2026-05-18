package com.example.kavyakanaja.presentation.poem_detail

import com.example.kavyakanaja.domain.model.Poem

data class PoemDetailState(
    val isLoading: Boolean = false,
    val poem: Poem? = null,
    val error: String = "",
    val isPlayingAudio: Boolean = false,
    val currentWordMeaning: Pair<String, String>? = null // To show meaning of difficult word on tap
)
