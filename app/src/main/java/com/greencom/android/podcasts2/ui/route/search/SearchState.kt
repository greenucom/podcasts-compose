package com.greencom.android.podcasts2.ui.route.search

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager

class SearchState(
    val scaffoldState: ScaffoldState,
    val searchResultsLazyColumnState: LazyListState,
    private val focusManager: FocusManager,
) {

    fun handleSideEffect(sideEffect: SearchViewModel.ViewSideEffect) = when (sideEffect) {
        SearchViewModel.ViewSideEffect.ClearTextFieldFocus -> focusManager.clearFocus()
    }

}

@Composable
fun rememberSearchState(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    searchResultsLazyColumnState: LazyListState = rememberLazyListState(),
    focusManager: FocusManager = LocalFocusManager.current,
) = remember(
    scaffoldState, searchResultsLazyColumnState, focusManager,
) {
    SearchState(
        scaffoldState = scaffoldState,
        searchResultsLazyColumnState = searchResultsLazyColumnState,
        focusManager = focusManager,
    )
}
