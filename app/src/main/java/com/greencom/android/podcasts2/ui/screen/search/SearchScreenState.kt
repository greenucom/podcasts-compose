package com.greencom.android.podcasts2.ui.screen.search

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
class SearchScreenState(
    val scaffoldState: ScaffoldState,
    val searchResultListState: LazyListState,
    val coroutineScope: CoroutineScope,
    val focusManager: FocusManager,
    val searchFieldFocusRequester: FocusRequester,
    val keyboardController: SoftwareKeyboardController?,
) {

    private val onScrollJob = MutableStateFlow<Job?>(null)

    fun handleSideEffect(sideEffect: SearchViewSideEffect) = when (sideEffect) {
        SearchViewSideEffect.RequestSearchFieldFocus -> {
            searchFieldFocusRequester.requestFocus()
            keyboardController?.show()
        }

        SearchViewSideEffect.ClearSearchFieldFocus -> focusManager.clearFocus()
    }

    fun onScroll() {
        if (onScrollJob.value?.isCompleted != false) {
            onScrollJob.update {
                coroutineScope.launch {
                    focusManager.clearFocus()
                    delay(OnScrollRepetitionsDelay)
                }
            }
        }
    }

    companion object {
        private const val OnScrollRepetitionsDelay = 500L
    }

}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun rememberSearchScreenState(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    searchResultListState: LazyListState = rememberLazyListState(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    focusManager: FocusManager = LocalFocusManager.current,
    searchFieldFocusRequester: FocusRequester = remember { FocusRequester() },
    keyboardController: SoftwareKeyboardController? = LocalSoftwareKeyboardController.current,
) = remember(
    scaffoldState, searchResultListState, coroutineScope, focusManager,
    searchFieldFocusRequester, keyboardController,
) {
    SearchScreenState(
        scaffoldState = scaffoldState,
        searchResultListState = searchResultListState,
        coroutineScope = coroutineScope,
        focusManager = focusManager,
        searchFieldFocusRequester = searchFieldFocusRequester,
        keyboardController = keyboardController,
    )
}
