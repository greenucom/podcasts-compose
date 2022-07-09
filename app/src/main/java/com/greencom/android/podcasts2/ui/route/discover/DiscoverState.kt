package com.greencom.android.podcasts2.ui.route.discover

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.greencom.android.podcasts2.ui.common.fastScrollTo
import com.greencom.android.podcasts2.ui.navigation.NavigationItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.launch

class DiscoverState(
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
            val firstVisibleItemIndex = trendingPodcastsLazyColumnState.firstVisibleItemIndex
            scrollToTop(animate = firstVisibleItemIndex <= MAX_POSITION_FOR_SMOOTH_SCROLL)
        }

        return true
    }

    fun handleSideEffect(sideEffect: DiscoverViewModel.ViewSideEffect) = when (sideEffect) {
        DiscoverViewModel.ViewSideEffect.ScrollToTop -> scrollToTop(animate = false)
        is DiscoverViewModel.ViewSideEffect.PodcastLongClicked -> { /* TODO: Navigate to dialog */ }
    }

    private fun scrollToTop(animate: Boolean) {
        scrollToTopJob.getAndUpdate {
            scope.launch {
                trendingPodcastsLazyColumnState.let {
                    if (animate) {
                        it.animateScrollToItem(0)
                    } else {
                        it.fastScrollTo(
                            instantScrollIndex = ITEM_INDEX_INSTANT_SCROLL_BEFORE_SMOOTH,
                            smoothScrollIndex = 0,
                        )
                    }
                }
            }
        }?.cancel()
    }

    companion object {
        private const val MAX_POSITION_FOR_SMOOTH_SCROLL = 10
        private const val ITEM_INDEX_INSTANT_SCROLL_BEFORE_SMOOTH = 2
    }

}

@Composable
fun rememberDiscoverState(
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    trendingPodcastsLazyColumnState: LazyListState = rememberLazyListState(),
) = remember(scaffoldState, trendingPodcastsLazyColumnState) {
    DiscoverState(
        scope = coroutineScope,
        scaffoldState = scaffoldState,
        trendingPodcastsLazyColumnState = trendingPodcastsLazyColumnState,
    )
}
