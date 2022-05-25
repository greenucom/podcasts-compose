package com.greencom.android.podcasts2.ui.screen.app

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.greencom.android.podcasts2.ui.screen.app.component.PodcastsBottomNavigationRespectingWindowInsets
import com.greencom.android.podcasts2.ui.screen.app.component.PodcastsNavHost
import com.greencom.android.podcasts2.ui.screen.app.component.PodcastsNavigationRailRespectingWindowInsets

@Composable
fun AppScreen(
    windowSizeClass: WindowSizeClass,
    modifier: Modifier = Modifier,
) {
    val screenState = rememberAppScreenState()

    Scaffold(
        modifier = modifier,
        bottomBar = {
            if (windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact) {
                PodcastsBottomNavigationRespectingWindowInsets(navController = screenState.navController)
            }
        },
    ) { paddingValues ->

        if (windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact) {
            PodcastsNavHost(
                modifier = Modifier.padding(paddingValues),
                navController = screenState.navController,
            )
        } else {
            Row(modifier = Modifier.padding(paddingValues)) {
                PodcastsNavigationRailRespectingWindowInsets(navController = screenState.navController)
                PodcastsNavHost(navController = screenState.navController)
            }
        }
    }
}
