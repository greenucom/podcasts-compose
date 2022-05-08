package com.greencom.android.podcasts2.ui.screen.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.greencom.android.podcasts2.ui.common.animatePlaceholderLoadingEffectColor
import com.greencom.android.podcasts2.ui.common.component.ErrorMessage
import com.greencom.android.podcasts2.ui.common.component.PodcastItem
import com.greencom.android.podcasts2.ui.common.component.PodcastItemPlaceholder
import com.greencom.android.podcasts2.ui.common.model.podcast.PodcastUiModel
import com.greencom.android.podcasts2.ui.common.screenbehavior.SpecificScreenBehavior
import com.greencom.android.podcasts2.ui.screen.search.component.SearchEmptyMessage
import com.greencom.android.podcasts2.ui.screen.search.component.SearchTopBar
import com.greencom.android.podcasts2.ui.theme.onSurfaceUtil

private const val KeyLoading = "Loading"
private const val KeyEmpty = "Empty"
private const val KeyError = "Error"

private const val LoadingPlaceholderCount = 5

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchScreen(
    navigateToPodcastScreen: (PodcastUiModel) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SearchMviViewModel = hiltViewModel(),
) {

    SpecificScreenBehavior {
        onBottomNavBarItemReselected = {
            val event = SearchViewEvent.RequestSearchFieldFocus
            viewModel.dispatchEvent(event)
            true
        }
    }

    val screenState = rememberSearchScreenState()

    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.sideEffects.collect(screenState::handleSideEffect)
    }

    state.showPodcastScreen?.let {
        navigateToPodcastScreen(it)
        val event = SearchViewEvent.PodcastScreenShown
        viewModel.dispatchEvent(event)
    }

    Scaffold(
        scaffoldState = screenState.scaffoldState,
        topBar = {
            SearchTopBar(
                modifier = modifier.focusRequester(screenState.searchFieldFocusRequester),
                query = state.query,
                onQueryChanged = {
                    val event = SearchViewEvent.QueryChanged(it)
                    viewModel.dispatchEvent(event)
                },
                onImeSearch = {
                    val event = SearchViewEvent.SearchPodcasts
                    viewModel.dispatchEvent(event)
                },
                onClearQuery = {
                    val event = SearchViewEvent.ClearQuery
                    viewModel.dispatchEvent(event)
                },
            )
        },
    ) { paddingValues ->

        if (screenState.searchResultListState.isScrollInProgress) {
            screenState.onScroll()
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = screenState.searchResultListState,
            contentPadding = paddingValues,
        ) {
            state.resultState.let { state ->
                when (state) {

                    is SearchResultState.Success -> {
                        itemsIndexed(
                            items = state.podcasts,
                            key = { _, podcast -> podcast.id },
                        ) { index, podcast ->
                            PodcastItem(
                                podcast = podcast,
                                onPodcastClicked = {
                                    val event = SearchViewEvent.ShowPodcastScreen(it)
                                    viewModel.dispatchEvent(event)
                                },
                                onSubscribedChanged = {
                                    val event = SearchViewEvent.ChangeSubscription(it)
                                    viewModel.dispatchEvent(event)
                                },
                            )

                            if (index != state.podcasts.lastIndex) {
                                Divider(color = MaterialTheme.colors.onSurfaceUtil)
                            }
                        }
                    }

                    SearchResultState.Loading -> {
                        item(key = KeyLoading) {
                            Column {
                                val placeholderLoadingColor by animatePlaceholderLoadingEffectColor()
                                repeat(LoadingPlaceholderCount) { index ->
                                    PodcastItemPlaceholder(loadingColor = placeholderLoadingColor)

                                    if (index != LoadingPlaceholderCount - 1) {
                                        Divider(color = MaterialTheme.colors.onSurfaceUtil)
                                    }
                                }
                            }
                        }
                    }

                    SearchResultState.Empty -> {
                        item(key = KeyEmpty) {
                            SearchEmptyMessage(modifier = Modifier.padding(vertical = 32.dp))
                        }
                    }

                    SearchResultState.Error -> {
                        item(key = KeyError) {
                            ErrorMessage(
                                modifier = Modifier.padding(vertical = 32.dp),
                                onTryAgainClicked = {
                                    val event = SearchViewEvent.SearchPodcasts
                                    viewModel.dispatchEvent(event)
                                },
                            )
                        }
                    }
                }
            }
        }
    }
}
