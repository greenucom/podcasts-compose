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
import com.greencom.android.podcasts2.domain.podcast.Podcast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
class SearchScreenState(
    val onPodcastClicked: (Podcast) -> Unit,
    val scaffoldState: ScaffoldState,
    val searchResultListState: LazyListState,
    val coroutineScope: CoroutineScope,
    val focusManager: FocusManager,
    val searchFieldFocusRequester: FocusRequester,
    val keyboardController: SoftwareKeyboardController?,
) {

    private var onScrollJob: Job? = null

    fun handleViewEvent(event: SearchViewModel.ViewEvent) {
        when (event) {
            SearchViewModel.ViewEvent.RequestFocusForSearchField -> {
                searchFieldFocusRequester.requestFocus()
            }

            SearchViewModel.ViewEvent.ClearFocusForSearchField -> focusManager.clearFocus()

            is SearchViewModel.ViewEvent.ShowPodcast -> onPodcastClicked(event.podcast)
        }
    }

    fun onScroll() {
        if (onScrollJob?.isCompleted != false) {
            onScrollJob = coroutineScope.launch {
                focusManager.clearFocus()
                delay(OnScrollRepetitionsDelay)
            }
        }
    }

    fun onBottomNavBarItemReselected(): Boolean {
        searchFieldFocusRequester.requestFocus()
        keyboardController?.show()
        return true
    }

    companion object {
        private const val OnScrollRepetitionsDelay = 500L
    }

}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun rememberSearchScreenState(
    onPodcastClicked: (Podcast) -> Unit,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    searchResultListState: LazyListState = rememberLazyListState(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    focusManager: FocusManager = LocalFocusManager.current,
    searchFieldFocusRequester: FocusRequester = remember { FocusRequester() },
    keyboardController: SoftwareKeyboardController? = LocalSoftwareKeyboardController.current,
) = remember(
    onPodcastClicked, scaffoldState, searchResultListState, coroutineScope, focusManager,
    searchFieldFocusRequester, keyboardController,
) {
    SearchScreenState(
        onPodcastClicked = onPodcastClicked,
        scaffoldState = scaffoldState,
        searchResultListState = searchResultListState,
        coroutineScope = coroutineScope,
        focusManager = focusManager,
        searchFieldFocusRequester = searchFieldFocusRequester,
        keyboardController = keyboardController,
    )
}
