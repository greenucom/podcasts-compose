package com.greencom.android.podcasts2.ui.route.search

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SearchRoute(
    modifier: Modifier = Modifier,
) {
    val searchState = rememberSearchState()

    Scaffold(
        modifier = modifier,
        scaffoldState = searchState.scaffoldState,
    ) { paddingValues ->

    }
}
