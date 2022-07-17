package com.greencom.android.podcasts2.ui.route.search

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.greencom.android.podcasts2.ui.common.CrossfadeTyped
import com.greencom.android.podcasts2.ui.common.animatePlaceholderLoadingColor
import com.greencom.android.podcasts2.ui.common.component.ConnectionError
import com.greencom.android.podcasts2.ui.common.component.podcast.PodcastItem
import com.greencom.android.podcasts2.ui.common.component.podcast.PodcastItemPlaceholder
import com.greencom.android.podcasts2.ui.route.search.component.SearchTextField
import com.greencom.android.podcasts2.ui.theme.onSurfaceUtil

private const val PodcastItemPlaceholderCount = 5

private const val ContentTypePodcastItem = "ContentTypePodcastItem"

@Composable
fun SearchRoute(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel(),
) {
    val searchState = rememberSearchState()

    val viewState = viewModel.state.collectAsState()

    Scaffold(
        modifier = modifier,
        scaffoldState = searchState.scaffoldState,
        topBar = {
            SearchTopBar(
                textFieldValue = viewState.value.textFieldValue,
                dispatchEvent = viewModel::dispatchEvent,
            )
        }
    ) { paddingValues ->

        CrossfadeTyped(
            modifier = Modifier.padding(paddingValues),
            targetState = viewState.value.searchResultsState,
        ) { searchResultsState ->
            when (searchResultsState) {
                SearchViewModel.SearchResultsState.QueryIsEmpty -> {
                    // TODO
                    Text("QueryIsEmpty")
                }

                SearchViewModel.SearchResultsState.Loading -> Loading()

                is SearchViewModel.SearchResultsState.Success -> {
                    Success(
                        modifier = Modifier.fillMaxSize(),
                        state = searchResultsState,
                        dispatchEvent = viewModel::dispatchEvent,
                        searchResultsLazyColumnState = searchState.searchResultsLazyColumnState,
                    )
                }

                SearchViewModel.SearchResultsState.NothingFound -> {
                    // TODO
                    Text("NothingFound")
                }

                is SearchViewModel.SearchResultsState.Error -> {
                    Error(
                        state = searchResultsState,
                        dispatchEvent = viewModel::dispatchEvent,
                    )
                }
            }
        }
    }
}

@Composable
fun SearchTopBar(
    textFieldValue: String,
    dispatchEvent: (SearchViewModel.ViewEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        modifier = modifier.windowInsetsPadding(WindowInsets.statusBars),
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        contentPadding = PaddingValues(horizontal = 16.dp),
    ) {
        SearchTextField(
            modifier = Modifier.fillMaxWidth(),
            value = textFieldValue,
            onValueChanged = {
                val event = SearchViewModel.ViewEvent.TextFieldValueChanged(it)
                dispatchEvent(event)
            },
            onClearTextFieldClicked = {
                val event = SearchViewModel.ViewEvent.ClearTextField
                dispatchEvent(event)
            },
            onImeSearch = {
                val event = SearchViewModel.ViewEvent.SearchPodcasts
                dispatchEvent(event)
            },
        )
    }
}

@Composable
private fun Loading(modifier: Modifier = Modifier) {
    Column(modifier = modifier.verticalScroll(rememberScrollState())) {
        val placeholderLoadingColor by animatePlaceholderLoadingColor()
        repeat(PodcastItemPlaceholderCount) {
            PodcastItemPlaceholder(color = placeholderLoadingColor)

            if (it < PodcastItemPlaceholderCount - 1) {
                Divider(color = MaterialTheme.colors.onSurfaceUtil)
            }
        }
    }
}

@Composable
private fun Success(
    state: SearchViewModel.SearchResultsState.Success,
    dispatchEvent: (SearchViewModel.ViewEvent) -> Unit,
    searchResultsLazyColumnState: LazyListState,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
        state = searchResultsLazyColumnState,
    ) {
        itemsIndexed(
            items = state.podcasts,
            key = { _, podcast -> podcast.id },
            contentType = { _, _ -> ContentTypePodcastItem},
        ) { idx, podcast ->
            PodcastItem(
                modifier = modifier.fillMaxWidth(),
                podcast = podcast,
                onPodcastClicked = { /* TODO: Open podcast */ },
                onIsUserSubscribedChanged = {
                    val event = SearchViewModel.ViewEvent.UpdateSubscriptionToPodcast(it)
                    dispatchEvent(event)
                },
            )

            if (idx != state.podcasts.lastIndex) {
                Divider(color = MaterialTheme.colors.onSurfaceUtil)
            }
        }
    }
}

@Suppress("UNUSED_PARAMETER")
@Composable
private fun Error(
    state: SearchViewModel.SearchResultsState.Error,
    dispatchEvent: (SearchViewModel.ViewEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    ConnectionError(
        modifier = modifier.fillMaxSize().padding(horizontal = 16.dp),
        onTryAgainClicked = {
            val event = SearchViewModel.ViewEvent.SearchPodcasts
            dispatchEvent(event)
        }
    )
}
