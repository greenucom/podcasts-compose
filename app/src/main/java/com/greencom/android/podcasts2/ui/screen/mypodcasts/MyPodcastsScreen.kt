package com.greencom.android.podcasts2.ui.screen.mypodcasts

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import com.greencom.android.podcasts2.ui.common.BottomNavBarState

@Composable
fun MyPodcastsScreen(
    bottomNavBarState: MutableState<BottomNavBarState>,
    modifier: Modifier = Modifier,
) {

    LaunchedEffect(Unit) {
        bottomNavBarState.value = BottomNavBarState.Visible
    }
}
