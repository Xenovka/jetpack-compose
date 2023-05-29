package com.dicoding.noideaapp.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.noideaapp.data.Repository
import com.dicoding.noideaapp.model.PowerRangers
import com.dicoding.noideaapp.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: Repository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<PowerRangers>>> = MutableStateFlow(UiState.Loading)

    val uiState: StateFlow<UiState<List<PowerRangers>>>
        get() = _uiState

    fun getAllRangers() {
        viewModelScope.launch {
            repository.getAllRangers()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { powerRangers ->
                    _uiState.value = UiState.Success(powerRangers)
                }
        }
    }
}