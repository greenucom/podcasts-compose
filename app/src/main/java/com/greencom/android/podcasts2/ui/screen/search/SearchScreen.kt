package com.greencom.android.podcasts2.ui.screen.search

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.greencom.android.podcasts2.ui.screen.search.component.SearchTopBar

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    searchViewModel: SearchViewModel = hiltViewModel(),
) {
    val screenState = rememberSearchScreenState()

    Scaffold(
        modifier = modifier,
        scaffoldState = screenState.scaffoldState,
        topBar = {
            SearchTopBar(
                searchValue = "",
                onSearchValueChanged = {},
                onSearch = {}
            )
        },
    ) { paddingValues ->

    }
}
