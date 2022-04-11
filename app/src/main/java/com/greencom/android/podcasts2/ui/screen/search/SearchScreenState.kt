package com.greencom.android.podcasts2.ui.screen.search

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalFocusManager

class SearchScreenState(
    val scaffoldState: ScaffoldState,
    val searchResultListState: LazyListState,
    val focusManager: FocusManager,
    val searchFieldFocusRequester: FocusRequester,
) {

    fun handleEvent(event: SearchViewModel.ViewEvent) {
        when (event) {
            SearchViewModel.ViewEvent.RequestInitialFocusForSearchField -> {
                searchFieldFocusRequester.requestFocus()
            }

            SearchViewModel.ViewEvent.ClearFocusForSearchField -> focusManager.clearFocus()
        }
    }

}

@Composable
fun rememberSearchScreenState(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    searchResultListState: LazyListState = rememberLazyListState(),
    focusManager: FocusManager = LocalFocusManager.current,
    searchFieldFocusRequester: FocusRequester = remember { FocusRequester() },
) = remember(
    scaffoldState, searchResultListState, focusManager, searchFieldFocusRequester,
) {
    SearchScreenState(
        scaffoldState = scaffoldState,
        searchResultListState = searchResultListState,
        focusManager = focusManager,
        searchFieldFocusRequester = searchFieldFocusRequester,
    )
}
