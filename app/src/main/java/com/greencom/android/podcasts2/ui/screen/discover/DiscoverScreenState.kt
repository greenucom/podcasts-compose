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
    val coroutineScope: CoroutineScope,
    val scaffoldState: ScaffoldState,
    val lazyColumnState: LazyListState,
    val recommendedPodcastsLazyRowState: LazyListState,
) {

    private var lazyColumnScrollJob: Job? = null

    fun handleAppEvent(event: AppViewModel.Event) {
        when (event) {
            AppViewModel.Event.DiscoverBottomNavBarItemReselected -> {
                onDiscoverBottomNavBarItemReselected()
            }

            else -> {}
        }
    }

    private fun onDiscoverBottomNavBarItemReselected() {
        lazyColumnScrollJob?.cancel()

        if (
            lazyColumnState.firstVisibleItemIndex == 0 &&
            lazyColumnState.firstVisibleItemScrollOffset == 0
        ) {
            onSearchClicked()
        } else {
            lazyColumnScrollJob = coroutineScope.launch {
                lazyColumnState.animateScrollToItem(0)
            }
        }
    }

}

@Composable
fun rememberDiscoverScreenState(
    onSearchClicked: () -> Unit,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    lazyColumnState: LazyListState = rememberLazyListState(),
    recommendedPodcastsLazyRowState: LazyListState = rememberLazyListState(),
) = remember(
    onSearchClicked,
    scaffoldState,
    lazyColumnState,
    recommendedPodcastsLazyRowState,
) {
    DiscoverScreenState(
        onSearchClicked = onSearchClicked,
        coroutineScope = coroutineScope,
        scaffoldState = scaffoldState,
        lazyColumnState = lazyColumnState,
        recommendedPodcastsLazyRowState = recommendedPodcastsLazyRowState,
    )
}
