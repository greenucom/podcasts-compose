package com.greencom.android.podcasts2.ui.route.search

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController

@OptIn(ExperimentalComposeUiApi::class)
class SearchState(
    val scaffoldState: ScaffoldState,
    val searchResultsLazyColumnState: LazyListState,
    val textFieldFocusRequester: FocusRequester,
    private val focusManager: FocusManager,
    private val keyboardController: SoftwareKeyboardController?,
) {

    fun handleSideEffect(sideEffect: SearchViewModel.ViewSideEffect) = when (sideEffect) {
        SearchViewModel.ViewSideEffect.RequestTextFieldFocus -> {
            textFieldFocusRequester.requestFocus()
            keyboardController?.show()
        }

        SearchViewModel.ViewSideEffect.ClearTextFieldFocus -> focusManager.clearFocus()
    }

}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun rememberSearchState(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    searchResultsLazyColumnState: LazyListState = rememberLazyListState(),
    textFieldFocusRequester: FocusRequester = remember { FocusRequester() },
    focusManager: FocusManager = LocalFocusManager.current,
    keyboardController: SoftwareKeyboardController? = LocalSoftwareKeyboardController.current,
) = remember(
    scaffoldState,
    searchResultsLazyColumnState,
    textFieldFocusRequester,
    focusManager,
    keyboardController,
) {
    SearchState(
        scaffoldState = scaffoldState,
        searchResultsLazyColumnState = searchResultsLazyColumnState,
        textFieldFocusRequester = textFieldFocusRequester,
        focusManager = focusManager,
        keyboardController = keyboardController,
    )
}
