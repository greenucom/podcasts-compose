package com.greencom.android.podcasts2.ui.screen.app

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import com.greencom.android.podcasts2.ui.common.BottomNavBarState
import com.greencom.android.podcasts2.ui.navigation.*
import com.greencom.android.podcasts2.ui.screen.app.component.BottomNavBar

@Composable
fun AppScreen(
    modifier: Modifier = Modifier,
    appViewModel: AppViewModel = hiltViewModel(),
) {
    val screenState = rememberAppScreenState()

    val bottomNavBarState = remember { mutableStateOf(BottomNavBarState.Visible) }

    Scaffold(
        modifier = modifier,
        scaffoldState = screenState.scaffoldState,
        bottomBar = {
            BottomNavBar(
                navController = screenState.navController,
                onItemReselected = appViewModel::onBottomNavBarItemReselected,
                state = bottomNavBarState.value,
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
                bottomNavBarState = bottomNavBarState,
            )

            discoverNavGraph(
                navController = screenState.navController,
                bottomNavBarState = bottomNavBarState,
                appViewModel = appViewModel,
            )

            libraryNavGraph(
                navController = screenState.navController,
                bottomNavBarState = bottomNavBarState,
            )

            profileNavGraph(
                navController = screenState.navController,
                bottomNavBarState = bottomNavBarState
            )
        }
    }
}
