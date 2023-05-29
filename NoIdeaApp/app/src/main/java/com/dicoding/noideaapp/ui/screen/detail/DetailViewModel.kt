package com.dicoding.noideaapp.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.noideaapp.data.Repository
import com.dicoding.noideaapp.model.PowerRangers
import com.dicoding.noideaapp.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: Repository
): ViewModel() {
    private val _uiState: MutableStateFlow<UiState<PowerRangers>> =
        MutableStateFlow(UiState.Loading)

    val uiState: StateFlow<UiState<PowerRangers>>
        get() = _uiState

    fun getRangerById(rangerId: String) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getRangerById(rangerId))
        }
    }
}