package com.greencom.android.podcasts2.ui.screen.discover

import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

class DiscoverScreenState(
    val scaffoldState: ScaffoldState,
)

@Composable
fun rememberDiscoverScreenState(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
) = remember(scaffoldState) {
    DiscoverScreenState(
        scaffoldState = scaffoldState,
    )
}
