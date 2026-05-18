package com.example.kavyakanaja.presentation.poet

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kavyakanaja.core.utils.Resource
import com.example.kavyakanaja.domain.repository.PoetryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PoetViewModel @Inject constructor(
    private val repository: PoetryRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(PoetState())
    val state: State<PoetState> = _state

    init {
        savedStateHandle.get<String>("poetId")?.let { poetId ->
            getPoetDetails(poetId)
        }
    }

    private fun getPoetDetails(poetId: String) {
        viewModelScope.launch {
            repository.getPoemsByPoet(poetId).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        val poems = result.data ?: emptyList()
                        val poet = poems.firstOrNull()?.poet
                        _state.value = PoetState(
                            poet = poet,
                            poems = poems
                        )
                    }
                    is Resource.Error -> {
                        _state.value = PoetState(
                            error = result.message ?: "An unexpected error occurred"
                        )
                    }
                    is Resource.Loading -> {
                        _state.value = PoetState(isLoading = true)
                    }
                }
            }
        }
    }
}
