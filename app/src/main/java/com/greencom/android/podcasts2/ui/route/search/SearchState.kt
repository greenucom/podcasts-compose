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
import com.greencom.android.podcasts2.domain.podcast.Podcast
import com.greencom.android.podcasts2.utils.join
import com.greencom.android.podcasts2.utils.relaunchIn
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
    private val navigateToPodcastRoute: (Podcast) -> Unit,
) {

    private val scrollSearchResultsToTopJob = MutableStateFlow<Job?>(null)

    fun handleSideEffect(sideEffect: SearchViewModel.ViewSideEffect) = when (sideEffect) {
        SearchViewModel.ViewSideEffect.RequestTextFieldFocus -> requestTextFieldFocus()
        SearchViewModel.ViewSideEffect.ClearTextFieldFocus -> focusManager.clearFocus()
        SearchViewModel.ViewSideEffect.ScrollSearchResultsToTop -> scrollSearchResultsToTop()
        is SearchViewModel.ViewSideEffect.NavigateToPodcastRoute ->
            navigateToPodcastRoute(sideEffect.podcast)
    }

    private fun requestTextFieldFocus() {
        coroutineScope.launch {
            scrollSearchResultsToTopJob.join()
            textFieldFocusRequester.requestFocus()
            keyboardController?.show()
        }
    }

    private fun scrollSearchResultsToTop() {
        scrollSearchResultsToTopJob.relaunchIn(coroutineScope) {
            searchResultsLazyColumnState.run {
                if (firstVisibleItemIndex > MaxFirstVisibleItemIndexForSmoothScroll) {
                    scrollToItem(ScrollToItemIndex)
                }
                animateScrollToItem(0)
            }
        }
    }

    companion object {
        private const val MaxFirstVisibleItemIndexForSmoothScroll = 4
        private const val ScrollToItemIndex = 2
    }

}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun rememberSearchState(
    navigateToPodcastRoute: (Podcast) -> Unit,
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
    navigateToPodcastRoute,
) {
    SearchState(
        coroutineScope = coroutineScope,
        scaffoldState = scaffoldState,
        searchResultsLazyColumnState = searchResultsLazyColumnState,
        textFieldFocusRequester = textFieldFocusRequester,
        focusManager = focusManager,
        keyboardController = keyboardController,
        navigateToPodcastRoute = navigateToPodcastRoute,
    )
}
