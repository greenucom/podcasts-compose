package com.greencom.android.podcasts2.ui.route.discover

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.greencom.android.podcasts2.ui.common.fastScroll
import com.greencom.android.podcasts2.utils.cancel
import com.greencom.android.podcasts2.utils.cancelAndLaunchIn
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow

class DiscoverState(
    val coroutineScope: CoroutineScope,
    val scaffoldState: ScaffoldState,
    val trendingPodcastsLazyColumnState: LazyListState,
    private val navigateToSearchRoute: () -> Unit,
) {

    private val scrollToTopJob = MutableStateFlow<Job?>(null)

    fun handleSideEffect(sideEffect: DiscoverViewModel.ViewSideEffect) = when (sideEffect) {
        DiscoverViewModel.ViewSideEffect.ScrollToTop -> scrollToTop(animate = false)
        DiscoverViewModel.ViewSideEffect.NavigateToSearchRoute -> navigateToSearchRoute()
        DiscoverViewModel.ViewSideEffect.NavigationItemReselected -> onNavigationItemReselected()
    }

    private fun onNavigationItemReselected() {
        scrollToTopJob.cancel()
        val isScrolledToTop = trendingPodcastsLazyColumnState.firstVisibleItemIndex == 0 &&
                trendingPodcastsLazyColumnState.firstVisibleItemScrollOffset == 0

        if (isScrolledToTop) {
            navigateToSearchRoute()
        } else {
            val firstVisibleItemIndex = trendingPodcastsLazyColumnState.firstVisibleItemIndex
            scrollToTop(animate = firstVisibleItemIndex <= MaxFirstVisibleItemIndexForSmoothScroll)
        }
    }

    private fun scrollToTop(animate: Boolean) {
        scrollToTopJob.cancelAndLaunchIn(coroutineScope) {
            trendingPodcastsLazyColumnState.run {
                if (animate) {
                    animateScrollToItem(0)
                } else {
                    fastScroll(
                        instantScrollIndex = FastScrollInstantItemIndex,
                        smoothScrollIndex = FastScrollSmoothItemIndex,
                    )
                }
            }
        }
    }

    companion object {
        private const val MaxFirstVisibleItemIndexForSmoothScroll = 6
        private const val FastScrollInstantItemIndex = 2
        private const val FastScrollSmoothItemIndex = 0
    }

}

@Composable
fun rememberDiscoverState(
    navigateToSearchRoute: () -> Unit,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    trendingPodcastsLazyColumnState: LazyListState = rememberLazyListState(),
) = remember(
    coroutineScope,
    scaffoldState,
    trendingPodcastsLazyColumnState,
    navigateToSearchRoute,
) {
    DiscoverState(
        coroutineScope = coroutineScope,
        scaffoldState = scaffoldState,
        trendingPodcastsLazyColumnState = trendingPodcastsLazyColumnState,
        navigateToSearchRoute = navigateToSearchRoute,
    )
}
