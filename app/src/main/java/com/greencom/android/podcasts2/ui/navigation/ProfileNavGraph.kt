package com.greencom.android.podcasts2.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation

fun NavGraphBuilder.profileNavGraph(navController: NavHostController) {
    navigation(route = BottomNavBarItem.Profile.route, startDestination = Screen.Profile.route) {
        composable(Screen.Profile.route) {  }
    }
}
