package com.greencom.android.podcasts2.ui.route.discover

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.greencom.android.podcasts2.ui.common.fastScroll
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
            scrollToTop(animate = firstVisibleItemIndex <= MaxPositionForSmoothScroll)
        }

        return true
    }

    fun handleSideEffect(sideEffect: DiscoverViewModel.ViewSideEffect) = when (sideEffect) {
        DiscoverViewModel.ViewSideEffect.ScrollToTop -> scrollToTop(animate = false)
    }

    private fun scrollToTop(animate: Boolean) {
        scrollToTopJob.getAndUpdate {
            scope.launch {
                trendingPodcastsLazyColumnState.let {
                    if (animate) {
                        it.animateScrollToItem(0)
                    } else {
                        it.fastScroll(
                            instantScrollIndex = ItemIndexInstantScrollBeforeSmooth,
                            smoothScrollIndex = 0,
                        )
                    }
                }
            }
        }?.cancel()
    }

    companion object {
        private const val MaxPositionForSmoothScroll = 8
        private const val ItemIndexInstantScrollBeforeSmooth = 2
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
