package com.greencom.android.podcasts2.ui.screen.search

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

class SearchScreenState(
    val scaffoldState: ScaffoldState,
    val searchResultListState: LazyListState,
)

@Composable
fun rememberSearchScreenState(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    searchResultListState: LazyListState = rememberLazyListState(),
) = remember(
    scaffoldState, searchResultListState,
) {
    SearchScreenState(
        scaffoldState = scaffoldState,
        searchResultListState = searchResultListState,
    )
}
