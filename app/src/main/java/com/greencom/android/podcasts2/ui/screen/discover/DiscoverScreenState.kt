package com.greencom.android.podcasts2.ui.screen.discover

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.greencom.android.podcasts2.ui.screen.app.AppViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class DiscoverScreenState(
    val onSearchClicked: () -> Unit,
    val scaffoldState: ScaffoldState,
    val lazyColumnState: LazyListState,
    val recommendedPodcastsLazyRowState: LazyListState,
) {

    private var lazyColumnScrollJob: Job? = null

    suspend fun handleAppEvent(event: AppViewModel.Event) {
        when (event) {
            AppViewModel.Event.DiscoverBottomNavBarItemReselected -> {
                lazyColumnScrollJob?.cancel()

                if (
                    lazyColumnState.firstVisibleItemIndex == 0 &&
                    lazyColumnState.firstVisibleItemScrollOffset == 0
                ) {
                    onSearchClicked()
                } else {
                    lazyColumnScrollJob = coroutineScope {
                        launch {
                            lazyColumnState.animateScrollToItem(0)
                        }
                    }
                }
            }

            else -> {}
        }
    }

}

@Composable
fun rememberDiscoverScreenState(
    onSearchClicked: () -> Unit,
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
        scaffoldState = scaffoldState,
        lazyColumnState = lazyColumnState,
        recommendedPodcastsLazyRowState = recommendedPodcastsLazyRowState,
    )
}
