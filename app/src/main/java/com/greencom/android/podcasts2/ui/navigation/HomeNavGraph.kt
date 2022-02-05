package com.greencom.android.podcasts2.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.greencom.android.podcasts2.ui.screens.app.components.BottomNavBarItem
import com.greencom.android.podcasts2.ui.screens.home.HomeScreen

fun NavGraphBuilder.homeNavGraph() {
    navigation(route = BottomNavBarItem.Home.route, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) { HomeScreen() }
    }
}
