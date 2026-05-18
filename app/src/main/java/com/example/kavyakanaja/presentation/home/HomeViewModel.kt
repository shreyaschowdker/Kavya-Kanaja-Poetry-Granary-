package com.example.kavyakanaja.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
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
class HomeViewModel @Inject constructor(
    private val repository: PoetryRepository
) : ViewModel() {

    private val _state = mutableStateOf(HomeState())
    val state: State<HomeState> = _state

    init {
        // Seed local DB from bundled assets on first launch
        viewModelScope.launch {
            repository.loadPoemsFromAssets()
            getPoemOfTheDay()
            getRecentPoems()
        }
    }

    private fun getPoemOfTheDay() {
        repository.getPoemOfTheDay().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = _state.value.copy(
                        poemOfTheDay = result.data,
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

    private fun getRecentPoems() {
        // Here you might just fetch normal poems and take top 5 for "recent"
        repository.getPoems().onEach { result ->
            if (result is Resource.Success) {
                _state.value = _state.value.copy(
                    recentPoems = result.data?.take(5) ?: emptyList()
                )
            }
        }.launchIn(viewModelScope)
    }
}
