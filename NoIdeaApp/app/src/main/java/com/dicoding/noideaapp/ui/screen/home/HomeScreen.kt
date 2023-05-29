package com.dicoding.noideaapp.ui.screen.home

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dicoding.noideaapp.di.Injection
import com.dicoding.noideaapp.model.PowerRangers
import com.dicoding.noideaapp.ui.ViewModelFactory
import com.dicoding.noideaapp.ui.common.UiState
import com.dicoding.noideaapp.ui.components.RangerListItem

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (String) -> Unit
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when(uiState) {
            is UiState.Loading -> {
                viewModel.getAllRangers()
            }
            is UiState.Success -> {
                HomeContent(
                    rangers = uiState.data,
                    modifier = modifier,
                    navigateToDetail = navigateToDetail
                )
            }
            is UiState.Error -> {}
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeContent(
    rangers: List<PowerRangers>,
    modifier: Modifier = Modifier,
    navigateToDetail: (String) -> Unit
) {
    Box(modifier = modifier) {
        val listState = rememberLazyListState()

        LazyColumn(
            state = listState
        ) {
            rangers.forEach{ _ ->
                items(rangers, key = { it.id }) { ranger ->
                    RangerListItem(
                        name = ranger.role,
                        photoUrl = ranger.photoUrl,
                        modifier = Modifier
                            .fillMaxWidth()
                            .animateItemPlacement(tween(durationMillis = 100))
                            .clickable {
                                navigateToDetail(ranger.id)
                            }
                    )
                }
            }
        }
    }
}