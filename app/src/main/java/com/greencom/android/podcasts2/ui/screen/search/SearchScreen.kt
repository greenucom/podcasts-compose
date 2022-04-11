package com.greencom.android.podcasts2.ui.screen.search

import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.insets.navigationBarsPadding
import com.greencom.android.podcasts2.domain.podcast.Podcast
import com.greencom.android.podcasts2.ui.common.animatePlaceholderLoadingEffectColor
import com.greencom.android.podcasts2.ui.common.component.ErrorMessage
import com.greencom.android.podcasts2.ui.common.component.PodcastItem
import com.greencom.android.podcasts2.ui.common.component.PodcastItemPlaceholder
import com.greencom.android.podcasts2.ui.screen.search.component.SearchEmptyMessage
import com.greencom.android.podcasts2.ui.screen.search.component.SearchTopBar
import com.greencom.android.podcasts2.ui.theme.onSurfaceUtil

private const val KeyLoading = "Loading"
private const val KeyEmpty = "Empty"
private const val KeyError = "Error"

private const val LoadingPlaceholderCount = 5

@Composable
fun SearchScreen(
    onPodcastClicked: (Podcast) -> Unit,
    modifier: Modifier = Modifier,
    searchViewModel: SearchViewModel = hiltViewModel(),
) {

    val screenState = rememberSearchScreenState()

    val viewState by searchViewModel.viewState.collectAsState()
    val query by searchViewModel.query.collectAsState()

    LaunchedEffect(Unit) {
        searchViewModel.viewEvents.collect { event ->
            screenState.handleEvent(event)
        }
    }

    Scaffold(
        scaffoldState = screenState.scaffoldState,
        topBar = {
            SearchTopBar(
                modifier = modifier.focusRequester(screenState.searchFieldFocusRequester),
                query = query,
                onQueryChanged = searchViewModel::onQueryChange,
                onImeSearch = searchViewModel::onImeSearch,
                onClearQuery = searchViewModel::onClearQuery,
            )
        },
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier.navigationBarsPadding(),
            state = screenState.searchResultListState,
            contentPadding = paddingValues,
        ) {
            viewState.let { state ->
                when (state) {

                    is SearchViewModel.ViewState.Success -> {
                        itemsIndexed(
                            items = state.podcasts,
                            key = { _, podcast -> podcast.id },
                        ) { index, podcast ->
                            PodcastItem(
                                podcast = podcast,
                                onPodcastClicked = onPodcastClicked,
                                onSubscribedChanged = searchViewModel::onSubscribedChanged,
                            )

                            if (index != state.podcasts.lastIndex) {
                                Divider(color = MaterialTheme.colors.onSurfaceUtil)
                            }
                        }
                    }

                    SearchViewModel.ViewState.Loading -> {
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

                    SearchViewModel.ViewState.Empty -> {
                        item(key = KeyEmpty) {
                            SearchEmptyMessage(modifier = Modifier.padding(vertical = 32.dp))
                        }
                    }

                    SearchViewModel.ViewState.Error -> {
                        item(key = KeyError) {
                            ErrorMessage(
                                modifier = Modifier.padding(vertical = 32.dp),
                                onTryAgainClicked = searchViewModel::onTryAgainClicked,
                            )
                        }
                    }
                }
            }
        }
    }
}
