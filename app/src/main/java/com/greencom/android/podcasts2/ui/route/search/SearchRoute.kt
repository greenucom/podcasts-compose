package com.greencom.android.podcasts2.ui.route.search

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.greencom.android.podcasts2.ui.route.search.component.SearchTextField

@Composable
fun SearchRoute(
    modifier: Modifier = Modifier,
) {
    val searchState = rememberSearchState()

    Scaffold(
        modifier = modifier,
        scaffoldState = searchState.scaffoldState,
        topBar = {
            // TODO
            SearchTopBar("", {})
        }
    ) { paddingValues ->

    }
}

@Composable
fun SearchTopBar(
    textFieldValue: String,
    dispatchEvent: () -> Unit,
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
                // TODO
            },
            onClearTextFieldClicked = {
                // TODO
            },
            onImeSearch = {
                // TODO
            },
        )
    }
}
