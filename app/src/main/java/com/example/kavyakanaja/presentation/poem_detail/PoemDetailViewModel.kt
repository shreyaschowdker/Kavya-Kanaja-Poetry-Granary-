package com.example.kavyakanaja.presentation.poem_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kavyakanaja.core.utils.Resource
import com.example.kavyakanaja.domain.repository.PoetryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PoemDetailViewModel @Inject constructor(
    private val repository: PoetryRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(PoemDetailState())
    val state: State<PoemDetailState> = _state

    init {
        savedStateHandle.get<String>("poemId")?.let { poemId ->
            getPoemDetail(poemId)
        }
    }

    private fun getPoemDetail(poemId: String) {
        repository.getPoemById(poemId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = _state.value.copy(
                        poem = result.data,
                        isLoading = false
                    )
                }
                is Resource.Error -> {
                    _state.value = _state.value.copy(
                        error = result.message ?: "An unexpected error occurred",
                        isLoading = false
                    )
                }
                is Resource.Loading -> {
                    _state.value = _state.value.copy(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun toggleFavorite() {
        _state.value.poem?.let { poem ->
            viewModelScope.launch {
                val newStatus = !poem.isFavorite
                repository.toggleFavorite(poem.id, newStatus)
                // In a full implementation, we'd rely on flow updates.
                // For immediate UI update, we update the state directly:
                _state.value = _state.value.copy(
                    poem = poem.copy(isFavorite = newStatus)
                )
            }
        }
    }

    fun playAudio() {
        _state.value = _state.value.copy(isPlayingAudio = !_state.value.isPlayingAudio)
        // Media player integration goes here
    }

    fun showWordMeaning(word: String) {
        val meaningObj = _state.value.poem?.difficultWords?.find { it.word == word }
        if (meaningObj != null) {
            _state.value = _state.value.copy(currentWordMeaning = Pair(meaningObj.word, meaningObj.meaning))
        }
    }

    fun dismissWordMeaning() {
        _state.value = _state.value.copy(currentWordMeaning = null)
    }
}
