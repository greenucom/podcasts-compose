package com.greencom.android.podcasts2.ui.screen.library

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import com.greencom.android.podcasts2.ui.common.BottomNavBarState

@Composable
fun LibraryScreen(
    bottomNavBarState: MutableState<BottomNavBarState>,
    modifier: Modifier = Modifier,
) {

    LaunchedEffect(Unit) {
        bottomNavBarState.value = BottomNavBarState.Visible
    }
}
