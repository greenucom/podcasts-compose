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
import com.greencom.android.podcasts2.utils.cancel
import com.greencom.android.podcasts2.utils.cancelAndLaunchIn
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow

class DiscoverState(
    val navigateToSearchRoute: () -> Unit,
    val coroutineScope: CoroutineScope,
    val scaffoldState: ScaffoldState,
    val trendingPodcastsLazyColumnState: LazyListState,
) {

    private val scrollToTopJob = MutableStateFlow<Job?>(null)

    @Suppress("UNUSED_PARAMETER")
    fun onNavigationItemReselected(navigationItem: NavigationItem): Boolean {
        scrollToTopJob.cancel()
        val isScrolledToTop = trendingPodcastsLazyColumnState.firstVisibleItemIndex == 0 &&
                trendingPodcastsLazyColumnState.firstVisibleItemScrollOffset == 0

        if (isScrolledToTop) {
            navigateToSearchRoute()
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
        scrollToTopJob.cancelAndLaunchIn(coroutineScope) {
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
    }

    companion object {
        private const val MaxPositionForSmoothScroll = 8
        private const val ItemIndexInstantScrollBeforeSmooth = 2
    }

}

@Composable
fun rememberDiscoverState(
    navigateToSearchRoute: () -> Unit,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    trendingPodcastsLazyColumnState: LazyListState = rememberLazyListState(),
) = remember(
    navigateToSearchRoute, coroutineScope, scaffoldState, trendingPodcastsLazyColumnState
) {
    DiscoverState(
        navigateToSearchRoute = navigateToSearchRoute,
        coroutineScope = coroutineScope,
        scaffoldState = scaffoldState,
        trendingPodcastsLazyColumnState = trendingPodcastsLazyColumnState,
    )
}
