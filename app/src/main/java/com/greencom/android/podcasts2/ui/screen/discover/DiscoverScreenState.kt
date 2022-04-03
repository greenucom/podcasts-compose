package com.greencom.android.podcasts2.ui.screen.discover

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.greencom.android.podcasts2.ui.screen.app.AppViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class DiscoverScreenState(
    val onSearchClicked: () -> Unit,
    val scaffoldState: ScaffoldState,
    val screenLazyListState: LazyListState,
    val coroutineScope: CoroutineScope,
    val recommendedPodcastsLazyListState: LazyListState,
) {

    private var scrollJob: Job? = null

    fun handleAppEvent(event: AppViewModel.ViewEvent) {
        when (event) {
            AppViewModel.ViewEvent.DiscoverBottomNavBarItemReselected -> {
                onDiscoverBottomNavBarItemReselected()
            }

            else -> {}
        }
    }

    private fun onDiscoverBottomNavBarItemReselected() {
        scrollJob?.cancel()

        if (
            screenLazyListState.firstVisibleItemIndex == 0 &&
            screenLazyListState.firstVisibleItemScrollOffset == 0
        ) {
            onSearchClicked()
        } else {
            scrollJob = coroutineScope.launch {
                screenLazyListState.animateScrollToItem(0)
            }
        }
    }

}

@Composable
fun rememberDiscoverScreenState(
    onSearchClicked: () -> Unit,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    screenLazyColumnState: LazyListState = rememberLazyListState(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    recommendedPodcastsLazyListState: LazyListState = rememberLazyListState(),
) = remember(
    onSearchClicked, scaffoldState, screenLazyColumnState, coroutineScope,
    recommendedPodcastsLazyListState,
) {
    DiscoverScreenState(
        onSearchClicked = onSearchClicked,
        scaffoldState = scaffoldState,
        screenLazyListState = screenLazyColumnState,
        coroutineScope = coroutineScope,
        recommendedPodcastsLazyListState = recommendedPodcastsLazyListState,
    )
}
