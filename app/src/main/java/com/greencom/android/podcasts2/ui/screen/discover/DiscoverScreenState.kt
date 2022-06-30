package com.greencom.android.podcasts2.ui.screen.discover

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.greencom.android.podcasts2.ui.navigation.NavigationItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.launch

class DiscoverScreenState(
    val scope: CoroutineScope,
    val scaffoldState: ScaffoldState,
    val trendingPodcastsLazyColumnState: LazyListState,
) {

    private val scrollToTopJob = MutableStateFlow<Job?>(null)

    @Suppress("UNUSED_PARAMETER")
    fun onNavigationItemReselected(navigationItem: NavigationItem): Boolean {
        scrollToTopJob.getAndUpdate { null }?.cancel()
        val isScrolledToTop = trendingPodcastsLazyColumnState.firstVisibleItemIndex == 0 &&
                trendingPodcastsLazyColumnState.firstVisibleItemScrollOffset == 0

        if (isScrolledToTop) {
            // TODO: Open search
        } else {
            scrollToTop()
        }

        return true
    }

    private fun scrollToTop() {
        scrollToTopJob.getAndUpdate {
            scope.launch {
                trendingPodcastsLazyColumnState.animateScrollToItem(0)
            }
        }?.cancel()
    }

}

@Composable
fun rememberDiscoverScreenState(
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    trendingPodcastsLazyColumnState: LazyListState = rememberLazyListState(),
) = remember(scaffoldState, trendingPodcastsLazyColumnState) {
    DiscoverScreenState(
        scope = coroutineScope,
        scaffoldState = scaffoldState,
        trendingPodcastsLazyColumnState = trendingPodcastsLazyColumnState,
    )
}
