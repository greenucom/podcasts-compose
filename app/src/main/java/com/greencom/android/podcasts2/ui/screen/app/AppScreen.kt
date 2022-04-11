package com.greencom.android.podcasts2.ui.screen.app

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.greencom.android.podcasts2.ui.common.ScreenBehavior
import com.greencom.android.podcasts2.ui.navigation.*
import com.greencom.android.podcasts2.ui.screen.app.component.BottomNavBar

@Composable
fun AppScreen(modifier: Modifier = Modifier) {

    val screenState = rememberAppScreenState()

    val (screenBehavior, onScreenBehaviorChanged) = remember {
        mutableStateOf(ScreenBehavior())
    }

    Scaffold(
        modifier = modifier,
        scaffoldState = screenState.scaffoldState,
        bottomBar = {
            BottomNavBar(
                navController = screenState.navController,
                state = screenBehavior.bottomNavBarState,
                onItemReselected = screenBehavior.onBottomNavBarItemReselected,
            )
        },
    ) { paddingValues ->

        NavHost(
            modifier = Modifier.padding(paddingValues),
            navController = screenState.navController,
            startDestination = BottomNavBarItem.MyPodcasts.route,
        ) {
            myPodcastsNavGraph(
                navController = screenState.navController,
                onScreenBehaviorChanged = onScreenBehaviorChanged,
            )

            discoverNavGraph(
                navController = screenState.navController,
                onScreenBehaviorChanged = onScreenBehaviorChanged,
            )

            libraryNavGraph(
                navController = screenState.navController,
                onScreenBehaviorChanged = onScreenBehaviorChanged,
            )

            profileNavGraph(
                navController = screenState.navController,
                onScreenBehaviorChanged = onScreenBehaviorChanged,
            )
        }
    }
}
