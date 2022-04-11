package com.greencom.android.podcasts2.ui.screen.app

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import com.greencom.android.podcasts2.ui.common.ScreenBehavior
import com.greencom.android.podcasts2.ui.navigation.*
import com.greencom.android.podcasts2.ui.screen.app.component.BottomNavBar

@Composable
fun AppScreen(modifier: Modifier = Modifier) {

    val screenState = rememberAppScreenState()

    val (currentScreenBehavior, onCurrentScreenBehaviorChanged) = remember {
        mutableStateOf(ScreenBehavior.Default)
    }

    val currentBackStackEntry by screenState.navController.currentBackStackEntryAsState()
    val currentDestinationId = currentBackStackEntry?.destination?.id

    // Reset currentScreenBehavior when navigating to another screen
    LaunchedEffect(currentDestinationId) {
        onCurrentScreenBehaviorChanged(ScreenBehavior.Default)
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

        NavHost(
            modifier = Modifier.padding(paddingValues),
            navController = screenState.navController,
            startDestination = BottomNavBarItem.MyPodcasts.route,
        ) {
            myPodcastsNavGraph(
                navController = screenState.navController,
                onScreenBehaviorChanged = onCurrentScreenBehaviorChanged,
            )

            discoverNavGraph(
                navController = screenState.navController,
                onScreenBehaviorChanged = onCurrentScreenBehaviorChanged,
            )

            libraryNavGraph(
                navController = screenState.navController,
                onScreenBehaviorChanged = onCurrentScreenBehaviorChanged,
            )

            profileNavGraph(
                navController = screenState.navController,
                onScreenBehaviorChanged = onCurrentScreenBehaviorChanged,
            )
        }
    }
}
