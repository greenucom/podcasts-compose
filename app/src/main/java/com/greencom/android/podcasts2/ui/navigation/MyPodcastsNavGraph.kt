package com.greencom.android.podcasts2.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation

fun NavGraphBuilder.myPodcastsNavGraph(navController: NavHostController) {
    navigation(
        route = NavigationItem.MyPodcasts.route,
        startDestination = Screen.MyPodcasts.scheme,
    ) {

        composable(route = Screen.MyPodcasts.scheme) {

        }

    }
}
