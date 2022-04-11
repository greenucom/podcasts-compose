package com.greencom.android.podcasts2.ui.screen.podcast

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.greencom.android.podcasts2.ui.common.BottomNavBarState

@Composable
fun PodcastScreen(
    bottomNavBarState: MutableState<BottomNavBarState>,
    modifier: Modifier = Modifier,
    podcastViewModel: PodcastViewModel = hiltViewModel(),
) {

    LaunchedEffect(Unit) {
        bottomNavBarState.value = BottomNavBarState.Gone
    }
}
