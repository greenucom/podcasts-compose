package com.greencom.android.podcasts2.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation

fun NavGraphBuilder.podcastsNavGraph(navController: NavHostController) {
    navigation(
        route = NavigationItem.Podcasts.route,
        startDestination = Screen.Podcasts.scheme,
    ) {

        composable(route = Screen.Podcasts.scheme) {

        }

    }
}
