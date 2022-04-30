package com.greencom.android.podcasts2.ui.screen.discover

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.greencom.android.podcasts2.domain.podcast.Podcast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class DiscoverScreenState(
    val onPodcastClicked: (Podcast) -> Unit,
    val onSearchClicked: () -> Unit,
    val scaffoldState: ScaffoldState,
    val screenListState: LazyListState,
    val coroutineScope: CoroutineScope,
    val recommendedPodcastsListState: LazyListState,
) {

    private var scrollJob: Job? = null

    fun onBottomNavBarItemReselected(): Boolean {
        scrollJob?.cancel()
        val isScrolledUp = screenListState.firstVisibleItemIndex == 0 &&
                screenListState.firstVisibleItemScrollOffset == 0

        if (isScrolledUp) {
            onSearchClicked()
        } else {
            scrollJob = coroutineScope.launch {
                screenListState.animateScrollToItem(0)
            }
        }
        return true
    }

    fun handleViewEvent(event: DiscoverViewModel.ViewEvent) = when (event) {
        is DiscoverViewModel.ViewEvent.ShowPodcast -> onPodcastClicked(event.podcast)
    }

}

@Composable
fun rememberDiscoverScreenState(
    onPodcastClicked: (Podcast) -> Unit,
    onSearchClicked: () -> Unit,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    screenLazyColumnState: LazyListState = rememberLazyListState(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    recommendedPodcastsLazyListState: LazyListState = rememberLazyListState(),
) = remember(
    onPodcastClicked, onSearchClicked, scaffoldState, screenLazyColumnState, coroutineScope,
    recommendedPodcastsLazyListState,
) {
    DiscoverScreenState(
        onPodcastClicked = onPodcastClicked,
        onSearchClicked = onSearchClicked,
        scaffoldState = scaffoldState,
        screenListState = screenLazyColumnState,
        coroutineScope = coroutineScope,
        recommendedPodcastsListState = recommendedPodcastsLazyListState,
    )
}
