package com.greencom.android.podcasts2.ui.screen.podcast

import androidx.compose.material.BackdropScaffoldState
import androidx.compose.material.BackdropValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBackdropScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@OptIn(ExperimentalMaterialApi::class)
class PodcastScreenState(
    val backdropScaffoldState: BackdropScaffoldState,
)

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun rememberPodcastScreenState(
    backdropScaffoldState: BackdropScaffoldState =
        rememberBackdropScaffoldState(initialValue = BackdropValue.Revealed),
) = remember(
    backdropScaffoldState
) {
    PodcastScreenState(
        backdropScaffoldState = backdropScaffoldState,
    )
}
