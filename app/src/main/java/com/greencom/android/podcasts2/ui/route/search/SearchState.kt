package com.greencom.android.podcasts2.ui.route.search

import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

class SearchState(
    val scaffoldState: ScaffoldState,
) {

}

@Composable
fun rememberSearchState(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
) = remember(scaffoldState) {
    SearchState(
        scaffoldState = scaffoldState,
    )
}
