package com.example.kavyakanaja.presentation.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kavyakanaja.core.utils.Resource
import com.example.kavyakanaja.domain.repository.PoetryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val repository: PoetryRepository
) : ViewModel() {

    private val _state = MutableStateFlow(FavoritesState())
    val state: StateFlow<FavoritesState> = _state.asStateFlow()

    init {
        getFavorites()
    }

    private fun getFavorites() {
        viewModelScope.launch {
            repository.getFavoritePoems().collect { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.update { 
                            it.copy(
                                favorites = result.data ?: emptyList(),
                                isLoading = false,
                                error = ""
                            )
                        }
                    }
                    is Resource.Error -> {
                        _state.update { 
                            it.copy(
                                error = result.message ?: "An unexpected error occurred",
                                isLoading = false
                            )
                        }
                    }
                    is Resource.Loading -> {
                        _state.update { it.copy(isLoading = true) }
                    }
                }
            }
        }
    }

    fun removeFavorite(poemId: String) {
        viewModelScope.launch {
            repository.toggleFavorite(poemId, false)
            // The flow from getFavorites() should automatically update the UI since Room emits updates
        }
    }
}
