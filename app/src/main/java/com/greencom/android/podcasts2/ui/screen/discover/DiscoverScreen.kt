package com.greencom.android.podcasts2.ui.screen.discover

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.greencom.android.podcasts2.ui.common.CrossfadeTyped
import com.greencom.android.podcasts2.ui.screen.discover.component.SearchPodcastsButton
import com.greencom.android.podcasts2.ui.screen.discover.component.TrendingCategorySelector

private const val KeyTrendingCategorySelector = "KeyTrendingCategorySelector"

private const val ContentTypeTrendingCategorySelector = "ContentTypeTrendingCategorySelector"

@Composable
fun DiscoverScreen(
    onSearchPodcastsClicked: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DiscoverViewModel = hiltViewModel(),
) {
    val screenState = rememberDiscoverScreenState()
    val viewState by viewModel.state.collectAsState()

    Scaffold(
        modifier = modifier,
        scaffoldState = screenState.scaffoldState,
        topBar = { DiscoverTopBar(onSearchPodcastsClicked = onSearchPodcastsClicked) },
    ) { paddingValues ->

        CrossfadeTyped(
            modifier = Modifier.padding(paddingValues),
            targetState = viewState,
        ) { state ->
            when (state) {
                DiscoverViewModel.ViewState.InitialLoading -> {}

                is DiscoverViewModel.ViewState.Success -> {
                    SuccessScreen(
                        state = state,
                        dispatchEvent = viewModel::dispatchEvent,
                        lazyColumnState = screenState.lazyColumnState,
                    )
                }
            }
        }
    }
}

@Composable
private fun DiscoverTopBar(
    onSearchPodcastsClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        val surfaceColor = MaterialTheme.colors.surface
        Spacer(
            modifier = Modifier
                .windowInsetsTopHeight(WindowInsets.statusBars)
                .fillMaxWidth()
                .drawBehind { drawRect(color = surfaceColor) }
        )

        TopAppBar(
            backgroundColor = MaterialTheme.colors.surface,
            elevation = 0.dp,
            contentPadding = PaddingValues(0.dp),
        ) {
            SearchPodcastsButton(
                modifier = Modifier.padding(horizontal = 16.dp),
                onClick = onSearchPodcastsClicked,
            )
        }
    }
}

@Composable
private fun SuccessScreen(
    state: DiscoverViewModel.ViewState.Success,
    dispatchEvent: (DiscoverViewModel.ViewEvent) -> Unit,
    modifier: Modifier = Modifier,
    lazyColumnState: LazyListState = rememberLazyListState(),
) {
    LazyColumn(modifier = modifier, state = lazyColumnState) {
        item(
            key = KeyTrendingCategorySelector,
            contentType = ContentTypeTrendingCategorySelector,
        ) {
            TrendingCategorySelector(
                selectableTrendingCategories = state.selectableTrendingCategories,
                onCategoryClicked = {
                    val event = DiscoverViewModel.ViewEvent.ToggleSelectableTrendingCategory(it)
                    dispatchEvent(event)
                },
                contentPadding = PaddingValues(horizontal = 16.dp),
            )
        }
    }
}
