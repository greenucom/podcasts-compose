package com.greencom.android.podcasts2.ui.screen.search

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.focus.FocusRequester

class SearchScreenState(
    val scaffoldState: ScaffoldState,
    val searchResultListState: LazyListState,
    val searchFieldFocusRequester: FocusRequester,
)

@Composable
fun rememberSearchScreenState(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    searchResultListState: LazyListState = rememberLazyListState(),
    searchFieldFocusRequester: FocusRequester = remember { FocusRequester() },
) = remember(
    scaffoldState, searchResultListState, searchFieldFocusRequester,
) {
    SearchScreenState(
        scaffoldState = scaffoldState,
        searchResultListState = searchResultListState,
        searchFieldFocusRequester = searchFieldFocusRequester,
    )
}
