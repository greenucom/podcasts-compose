package com.greencom.android.podcasts2.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.greencom.android.podcasts2.ui.screens.discover.DiscoverScreen

fun NavGraphBuilder.discoverNavGraph() {
    navigation(route = BottomNavBarItem.Discover.route, startDestination = Screen.Discover.route) {
        composable(Screen.Discover.route) { DiscoverScreen() }
    }
}
