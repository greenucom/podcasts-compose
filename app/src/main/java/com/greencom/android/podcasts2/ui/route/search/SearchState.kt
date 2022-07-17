package com.greencom.android.podcasts2.ui.route.search

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
import com.greencom.android.podcasts2.utils.cancel
import com.greencom.android.podcasts2.utils.cancelAndLaunchIn
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
class SearchState(
    val coroutineScope: CoroutineScope,
    val scaffoldState: ScaffoldState,
    val searchResultsLazyColumnState: LazyListState,
    val textFieldFocusRequester: FocusRequester,
    private val focusManager: FocusManager,
    private val keyboardController: SoftwareKeyboardController?,
) {

    private val scrollSearchResultsToTopJob = MutableStateFlow<Job?>(null)

    fun handleSideEffect(sideEffect: SearchViewModel.ViewSideEffect) = when (sideEffect) {
        SearchViewModel.ViewSideEffect.RequestTextFieldFocus -> requestTextFieldFocus()
        SearchViewModel.ViewSideEffect.ClearTextFieldFocus -> focusManager.clearFocus()
        SearchViewModel.ViewSideEffect.ScrollSearchResultsToTop -> scrollSearchResultsToTop()
        SearchViewModel.ViewSideEffect.NavigationItemReselected -> onNavigationItemReselected()
    }

    private fun requestTextFieldFocus() {
        textFieldFocusRequester.requestFocus()
        keyboardController?.show()
    }

    private fun scrollSearchResultsToTop() {
        scrollSearchResultsToTopJob.cancelAndLaunchIn(coroutineScope) {
            scrollSearchResultsToTopImpl()
        }
    }

    private fun onNavigationItemReselected() {
        scrollSearchResultsToTopJob.cancel()
        coroutineScope.launch {
            scrollSearchResultsToTopImpl()
            requestTextFieldFocus()
        }
    }

    private suspend fun scrollSearchResultsToTopImpl() {
        searchResultsLazyColumnState.run {
            if (firstVisibleItemIndex > MaxFirstVisibleItemIndexForSmoothScroll) {
                scrollToItem(ScrollToItemIndex)
            }
            animateScrollToItem(0)
        }
    }

    companion object {
        private const val MaxFirstVisibleItemIndexForSmoothScroll = 6
        private const val ScrollToItemIndex = 2
    }

}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun rememberSearchState(
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    searchResultsLazyColumnState: LazyListState = rememberLazyListState(),
    textFieldFocusRequester: FocusRequester = remember { FocusRequester() },
    focusManager: FocusManager = LocalFocusManager.current,
    keyboardController: SoftwareKeyboardController? = LocalSoftwareKeyboardController.current,
) = remember(
    coroutineScope,
    scaffoldState,
    searchResultsLazyColumnState,
    textFieldFocusRequester,
    focusManager,
    keyboardController,
) {
    SearchState(
        coroutineScope = coroutineScope,
        scaffoldState = scaffoldState,
        searchResultsLazyColumnState = searchResultsLazyColumnState,
        textFieldFocusRequester = textFieldFocusRequester,
        focusManager = focusManager,
        keyboardController = keyboardController,
    )
}
