package com.greencom.android.podcasts2.ui.route.search

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.greencom.android.podcasts2.ui.route.search.component.SearchTextField

@Composable
fun SearchRoute(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel(),
) {
    val searchState = rememberSearchState()

    val viewState = viewModel.state.collectAsState()

    Scaffold(
        modifier = modifier,
        scaffoldState = searchState.scaffoldState,
        topBar = {
            SearchTopBar(
                textFieldValue = viewState.value.textFieldValue,
                dispatchEvent = viewModel::dispatchEvent,
            )
        }
    ) { paddingValues ->

    }
}

@Composable
fun SearchTopBar(
    textFieldValue: String,
    dispatchEvent: (SearchViewModel.ViewEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        modifier = modifier.windowInsetsPadding(WindowInsets.statusBars),
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        contentPadding = PaddingValues(horizontal = 16.dp),
    ) {
        SearchTextField(
            modifier = Modifier.fillMaxWidth(),
            value = textFieldValue,
            onValueChanged = {
                val event = SearchViewModel.ViewEvent.TextFieldValueChanged(it)
                dispatchEvent(event)
            },
            onClearTextFieldClicked = {
                val event = SearchViewModel.ViewEvent.ClearTextField
                dispatchEvent(event)
            },
            onImeSearch = {
                val event = SearchViewModel.ViewEvent.SearchPodcasts
                dispatchEvent(event)
            },
        )
    }
}
