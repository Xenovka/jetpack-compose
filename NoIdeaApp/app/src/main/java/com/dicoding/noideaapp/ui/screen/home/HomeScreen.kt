package com.dicoding.noideaapp.ui.screen.home

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dicoding.noideaapp.di.Injection
import com.dicoding.noideaapp.ui.ViewModelFactory
import com.dicoding.noideaapp.ui.common.UiState
import com.dicoding.noideaapp.ui.components.RangerListItem
import com.dicoding.noideaapp.ui.components.SearchBar

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
                    modifier = modifier,
                    viewModel = viewModel,
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
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel,
    navigateToDetail: (String) -> Unit
) {
    val groupedRangers by viewModel.groupedRangers.collectAsState()
    val query by viewModel.query

    Box(modifier = modifier) {
        val listState = rememberLazyListState()

        LazyColumn(
            state = listState
        ) {
            item {
                SearchBar(
                    query = query,
                    onQueryChange = viewModel::search,
                    modifier = Modifier.background(MaterialTheme.colorScheme.primary)
                )
            }
            groupedRangers.forEach { (_, rangers) ->
                items(rangers) { ranger ->
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