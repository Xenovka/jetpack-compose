package com.dicoding.noideaapp.ui.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
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
    private val _groupedRangers = MutableStateFlow(
        repository.getRangers()
            .sortedBy { it.name }
            .groupBy { it.name[0] }
    )
    private val _query = mutableStateOf("")


    val uiState: StateFlow<UiState<List<PowerRangers>>>
        get() = _uiState

    val groupedRangers: StateFlow<Map<Char, List<PowerRangers>>> get() = _groupedRangers

    val query: State<String> get() = _query

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

    fun search(newQuery: String) {
        _query.value = newQuery
        _groupedRangers.value = repository.searchRangers(_query.value)
            .sortedBy { it.name }
            .groupBy { it.name[0] }
    }
}