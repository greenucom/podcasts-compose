package com.greencom.android.podcasts2.ui.screen.app

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.greencom.android.podcasts2.ui.common.screenbehavior.LocalScreenBehaviorController
import com.greencom.android.podcasts2.ui.common.screenbehavior.ScreenBehavior
import com.greencom.android.podcasts2.ui.common.screenbehavior.ScreenBehaviorController
import com.greencom.android.podcasts2.ui.navigation.*
import com.greencom.android.podcasts2.ui.screen.app.component.BottomNavBar

@Composable
fun AppScreen(modifier: Modifier = Modifier) {

    val screenState = rememberAppScreenState()

    var currentScreenBehavior by remember {
        mutableStateOf(ScreenBehavior.Default)
    }
    val screenBehaviorController = remember {
        object : ScreenBehaviorController {
            override fun set(screenBehavior: ScreenBehavior) {
                currentScreenBehavior = screenBehavior
            }

            override fun remove(screenBehavior: ScreenBehavior) {
                if (currentScreenBehavior == screenBehavior) {
                    currentScreenBehavior = ScreenBehavior.Default
                }
            }
        }
    }

    Scaffold(
        modifier = modifier,
        scaffoldState = screenState.scaffoldState,
        bottomBar = {
            BottomNavBar(
                navController = screenState.navController,
                state = currentScreenBehavior.bottomNavBarState,
                onItemReselected = currentScreenBehavior.onBottomNavBarItemReselected,
            )
        },
    ) { paddingValues ->

        CompositionLocalProvider(LocalScreenBehaviorController provides screenBehaviorController) {

            NavHost(
                modifier = Modifier.padding(paddingValues),
                navController = screenState.navController,
                startDestination = BottomNavBarItem.MyPodcasts.route,
            ) {
                myPodcastsNavGraph(
                    navController = screenState.navController,
                )

                discoverNavGraph(
                    navController = screenState.navController,
                )

                libraryNavGraph(
                    navController = screenState.navController,
                )

                profileNavGraph(
                    navController = screenState.navController,
                )
            }
        }
    }
}
