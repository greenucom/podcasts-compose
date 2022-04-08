package com.greencom.android.podcasts2.ui.screen.search

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.greencom.android.podcasts2.ui.screen.search.component.SearchTopBar

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    searchViewModel: SearchViewModel = hiltViewModel(),
) {

    val screenState = rememberSearchScreenState()

    val query by searchViewModel.query.collectAsState()

    Scaffold(
        modifier = modifier,
        scaffoldState = screenState.scaffoldState,
        topBar = {
            SearchTopBar(
                query = query,
                onQueryChanged = searchViewModel::onQueryChange,
                onImeSearch = searchViewModel::onImeSearch,
                onClearQuery = searchViewModel::onClearQuery,
            )
        }
    ) { paddingValues ->

    }
}
