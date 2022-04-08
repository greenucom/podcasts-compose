package com.greencom.android.podcasts2.ui.screen.search

import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

class SearchScreenState(
    val scaffoldState: ScaffoldState,
)

@Composable
fun rememberSearchScreenState(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
) = remember(
    scaffoldState,
) {
    SearchScreenState(
        scaffoldState = scaffoldState,
    )
}
