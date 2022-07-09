package com.greencom.android.podcasts2.ui.route.discover

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.greencom.android.podcasts2.ui.common.CrossfadeTyped
import com.greencom.android.podcasts2.ui.common.animatePlaceholderLoadingColor
import com.greencom.android.podcasts2.ui.common.component.ConnectionError
import com.greencom.android.podcasts2.ui.common.component.podcast.PodcastItem
import com.greencom.android.podcasts2.ui.common.component.podcast.PodcastItemPlaceholder
import com.greencom.android.podcasts2.ui.common.screenbehavior.SpecificScreenBehavior
import com.greencom.android.podcasts2.ui.route.discover.component.SearchPodcastsButton
import com.greencom.android.podcasts2.ui.route.discover.component.TrendingCategorySelector
import com.greencom.android.podcasts2.ui.theme.onSurfaceUtil

private const val PodcastItemPlaceholderCount = 5

private const val ContentTypePodcastItem = "ContentTypePodcastItem"

// TODO: Implement design for large screens

@Composable
fun DiscoverRoute(
    onSearchPodcastsClicked: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DiscoverViewModel = hiltViewModel(),
) {
    val discoverState = rememberDiscoverState()
    val viewState = viewModel.state.collectAsState()

    SpecificScreenBehavior {
        onNavigationItemReselected = discoverState::onNavigationItemReselected
    }

    LaunchedEffect(Unit) {
        viewModel.sideEffects.collect(discoverState::handleSideEffect)
    }

    Scaffold(
        modifier = modifier
            .windowInsetsPadding(WindowInsets.displayCutout.only(WindowInsetsSides.End))
            .windowInsetsPadding(WindowInsets.navigationBars.only(WindowInsetsSides.End)),
        scaffoldState = discoverState.scaffoldState,
        topBar = { DiscoverTopBar(onSearchPodcastsClicked = onSearchPodcastsClicked) },
    ) { paddingValues ->

        CrossfadeTyped(
            modifier = Modifier.padding(paddingValues),
            targetState = viewState.value,
        ) { state ->
            when (state) {
                DiscoverViewModel.ViewState.InitialLoading -> {
                    // TODO: Add initial loading screen
                }

                is DiscoverViewModel.ViewState.Success -> {
                    SuccessScreen(
                        state = state,
                        dispatchEvent = viewModel::dispatchEvent,
                        trendingPodcastsLazyColumnState = discoverState.trendingPodcastsLazyColumnState,
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
    TopAppBar(
        modifier = modifier.windowInsetsPadding(WindowInsets.statusBars),
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

@Composable
private fun SuccessScreen(
    state: DiscoverViewModel.ViewState.Success,
    dispatchEvent: (DiscoverViewModel.ViewEvent) -> Unit,
    modifier: Modifier = Modifier,
    trendingPodcastsLazyColumnState: LazyListState = rememberLazyListState(),
) {
    Column(modifier = modifier) {

        TrendingCategorySelector(
            selectableTrendingCategories = state.selectableTrendingCategories,
            onCategoryClicked = {
                val event = DiscoverViewModel.ViewEvent.ToggleSelectableTrendingCategory(it)
                dispatchEvent(event)
            },
            contentPadding = PaddingValues(horizontal = 16.dp),
        )

        CrossfadeTyped(targetState = state.trendingPodcastsState) { trendingPodcastsState ->
            when (trendingPodcastsState) {

                DiscoverViewModel.TrendingPodcastsState.Loading -> {
                    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                        val placeholderLoadingColor by animatePlaceholderLoadingColor()
                        repeat(PodcastItemPlaceholderCount) {
                            PodcastItemPlaceholder(color = placeholderLoadingColor)

                            if (it < PodcastItemPlaceholderCount - 1) {
                                Divider(color = MaterialTheme.colors.onSurfaceUtil)
                            }
                        }
                    }
                }

                is DiscoverViewModel.TrendingPodcastsState.Success -> {
                    LazyColumn(state = trendingPodcastsLazyColumnState) {
                        itemsIndexed(
                            items = trendingPodcastsState.trendingPodcasts,
                            key = { _, podcast -> podcast.id },
                            contentType = { _, _ -> ContentTypePodcastItem },
                        ) { idx, podcast ->
                            PodcastItem(
                                modifier = Modifier.fillMaxWidth(),
                                podcast = podcast,
                                onPodcastClicked = { /* TODO: Open podcast */ },
                                onIsUserSubscribedChanged = {
                                    val event = DiscoverViewModel.ViewEvent.UpdateSubscriptionToPodcast(it)
                                    dispatchEvent(event)
                                },
                                onPodcastLongClicked = {
                                    val event = DiscoverViewModel.ViewEvent.PodcastLongClicked(it)
                                    dispatchEvent(event)
                                }
                            )

                            if (idx != trendingPodcastsState.trendingPodcasts.lastIndex) {
                                Divider(color = MaterialTheme.colors.onSurfaceUtil)
                            }
                        }
                    }
                }

                DiscoverViewModel.TrendingPodcastsState.Error -> {
                    ConnectionError(
                        onTryAgainClicked = {
                            dispatchEvent(DiscoverViewModel.ViewEvent.RefreshTrendingPodcasts)
                        },
                    )
                }
            }
        }
    }
}
