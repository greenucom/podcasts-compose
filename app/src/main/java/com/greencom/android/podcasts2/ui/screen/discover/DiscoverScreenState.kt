package com.greencom.android.podcasts2.ui.screen.discover

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.launch

class DiscoverScreenState(
    val dispatchEvent: (DiscoverViewEvent) -> Unit,
    val scaffoldState: ScaffoldState,
    val screenListState: LazyListState,
    val coroutineScope: CoroutineScope,
    val recommendedPodcastsListState: LazyListState,
) {

    private val scrollJob = MutableStateFlow<Job?>(null)

    fun onBottomNavBarItemReselected(): Boolean {
        val isScrolledUp = screenListState.firstVisibleItemIndex == 0 &&
                screenListState.firstVisibleItemScrollOffset == 0

        if (isScrolledUp) {
            val event = DiscoverViewEvent.ShowSearchScreen
            dispatchEvent(event)
        } else {
            scrollJob.getAndUpdate {
                coroutineScope.launch {
                    screenListState.animateScrollToItem(0)
                }
            }?.cancel()
        }
        return true
    }

}

@Composable
fun rememberDiscoverScreenState(
    dispatchEvent: (DiscoverViewEvent) -> Unit,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    screenLazyColumnState: LazyListState = rememberLazyListState(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    recommendedPodcastsLazyListState: LazyListState = rememberLazyListState(),
) = remember(
    dispatchEvent, scaffoldState, screenLazyColumnState, coroutineScope,
    recommendedPodcastsLazyListState,
) {
    DiscoverScreenState(
        dispatchEvent = dispatchEvent,
        scaffoldState = scaffoldState,
        screenListState = screenLazyColumnState,
        coroutineScope = coroutineScope,
        recommendedPodcastsListState = recommendedPodcastsLazyListState,
    )
}
