package com.greencom.android.podcasts2.ui.navigation

import androidx.compose.runtime.MutableState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.greencom.android.podcasts2.ui.common.BottomNavBarState
import com.greencom.android.podcasts2.ui.screen.mypodcasts.MyPodcastsScreen

fun NavGraphBuilder.myPodcastsNavGraph(
    navController: NavHostController,
    bottomNavBarState: MutableState<BottomNavBarState>,
) {
    navigation(
        route = BottomNavBarItem.MyPodcasts.route,
        startDestination = Screen.MyPodcasts.route,
    ) {

        composable(Screen.MyPodcasts.route) {
            MyPodcastsScreen(
                bottomNavBarState = bottomNavBarState,
            )
        }

    }
}
