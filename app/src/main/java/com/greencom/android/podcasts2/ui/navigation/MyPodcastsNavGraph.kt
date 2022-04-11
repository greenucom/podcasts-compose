package com.greencom.android.podcasts2.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.greencom.android.podcasts2.ui.common.ScreenBehavior
import com.greencom.android.podcasts2.ui.screen.mypodcasts.MyPodcastsScreen

fun NavGraphBuilder.myPodcastsNavGraph(
    navController: NavHostController,
    onScreenBehaviorChanged: (ScreenBehavior) -> Unit,
) {
    navigation(
        route = BottomNavBarItem.MyPodcasts.route,
        startDestination = Screen.MyPodcasts.route,
    ) {

        composable(Screen.MyPodcasts.route) {
            MyPodcastsScreen()
        }

    }
}
