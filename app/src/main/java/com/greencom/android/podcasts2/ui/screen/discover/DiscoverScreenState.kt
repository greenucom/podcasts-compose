package com.greencom.android.podcasts2.ui.screen.discover

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

class DiscoverScreenState(
    val scaffoldState: ScaffoldState,
    val screenLazyColumnState: LazyListState,
    val recommendedPodcastsLazyRowState: LazyListState,
)

@Composable
fun rememberDiscoverScreenState(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    screenLazyColumnState: LazyListState = rememberLazyListState(),
    recommendedPodcastsLazyRowState: LazyListState = rememberLazyListState(),
) = remember {
    DiscoverScreenState(
        scaffoldState = scaffoldState,
        screenLazyColumnState = screenLazyColumnState,
        recommendedPodcastsLazyRowState = recommendedPodcastsLazyRowState,
    )
}
