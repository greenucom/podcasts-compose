package com.greencom.android.podcasts2.ui.route.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.greencom.android.podcasts2.R
import com.greencom.android.podcasts2.ui.common.CrossfadeTyped
import com.greencom.android.podcasts2.ui.common.animatePlaceholderLoadingColor
import com.greencom.android.podcasts2.ui.common.component.ConnectionError
import com.greencom.android.podcasts2.ui.common.component.NothingFoundError
import com.greencom.android.podcasts2.ui.common.component.podcast.PodcastItem
import com.greencom.android.podcasts2.ui.common.component.podcast.PodcastItemPlaceholder
import com.greencom.android.podcasts2.ui.common.screenbehavior.SpecificScreenBehavior
import com.greencom.android.podcasts2.ui.route.search.component.SearchTextField
import com.greencom.android.podcasts2.ui.theme.onSurfaceUtil

private const val PodcastItemPlaceholderCount = 5

private const val ContentTypePodcastItem = "ContentTypePodcastItem"

// TODO: Implement design for large screens

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchRoute(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel(),
) {
    val searchState = rememberSearchState()

    val viewState = viewModel.state.collectAsState()

    SpecificScreenBehavior {
        onNavigationItemReselected = {
            val event = SearchViewModel.ViewEvent.NavigationItemReselected
            viewModel.dispatchEvent(event)
            true
        }
    }

    LaunchedEffect(Unit) {
        viewModel.sideEffects.collect(searchState::handleSideEffect)
    }

    Scaffold(
        modifier = modifier,
        scaffoldState = searchState.scaffoldState,
        topBar = {
            SearchTopBar(
                textFieldValue = viewState.value.textFieldValue,
                dispatchEvent = viewModel::dispatchEvent,
                focusRequester = searchState.textFieldFocusRequester,
            )
        }
    ) { paddingValues ->

        CrossfadeTyped(
            modifier = Modifier.padding(paddingValues),
            targetState = viewState.value.searchResultsState,
        ) { searchResultsState ->
            when (searchResultsState) {
                SearchViewModel.SearchResultsState.QueryIsEmpty -> {
                    QueryIsEmpty(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp)
                            .windowInsetsPadding(WindowInsets.ime)
                    )
                }

                SearchViewModel.SearchResultsState.Loading -> {
                    Column {
                        val placeholderLoadingColor by animatePlaceholderLoadingColor()
                        repeat(PodcastItemPlaceholderCount) {
                            PodcastItemPlaceholder(color = placeholderLoadingColor)

                            if (it < PodcastItemPlaceholderCount - 1) {
                                Divider(color = MaterialTheme.colors.onSurfaceUtil)
                            }
                        }
                    }
                }

                is SearchViewModel.SearchResultsState.Success -> {
                    Success(
                        modifier = Modifier.fillMaxSize(),
                        state = searchResultsState,
                        dispatchEvent = viewModel::dispatchEvent,
                        searchResultsLazyColumnState = searchState.searchResultsLazyColumnState,
                    )
                }

                SearchViewModel.SearchResultsState.NothingFound -> {
                    NothingFoundError(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp)
                            .windowInsetsPadding(WindowInsets.ime)
                    )
                }

                is SearchViewModel.SearchResultsState.Error -> {
                    ConnectionError(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp)
                            .windowInsetsPadding(WindowInsets.ime),
                        onTryAgainClicked = {
                            val event = SearchViewModel.ViewEvent.SearchPodcasts
                            viewModel.dispatchEvent(event)
                        }
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
    focusRequester: FocusRequester,
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
            focusRequester = focusRequester,
        )
    }
}

@Composable
private fun QueryIsEmpty(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            modifier = Modifier
                .widthIn(max = 240.dp)
                .aspectRatio(1f),
            painter = painterResource(id = R.drawable.vec_image_search),
            contentDescription = stringResource(id = R.string.enter_search_query),
        )

        Text(
            modifier = Modifier.alpha(0.87f),
            text = stringResource(id = R.string.enter_search_query),
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
private fun Success(
    state: SearchViewModel.SearchResultsState.Success,
    dispatchEvent: (SearchViewModel.ViewEvent) -> Unit,
    searchResultsLazyColumnState: LazyListState,
    modifier: Modifier = Modifier,
) {
    if (searchResultsLazyColumnState.isScrollInProgress) {
        val event = SearchViewModel.ViewEvent.SearchResultsScrolled
        dispatchEvent(event)
    }

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
                modifier = Modifier.fillMaxWidth(),
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
