package com.greencom.android.podcasts2.ui.screen.discover

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

class DiscoverScreenState(
    val scaffoldState: ScaffoldState,
    val lazyColumnState: LazyListState,
)

@Composable
fun rememberDiscoverScreenState(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    lazyColumnState: LazyListState = rememberLazyListState(),
) = remember(scaffoldState, lazyColumnState) {
    DiscoverScreenState(
        scaffoldState = scaffoldState,
        lazyColumnState = lazyColumnState,
    )
}
