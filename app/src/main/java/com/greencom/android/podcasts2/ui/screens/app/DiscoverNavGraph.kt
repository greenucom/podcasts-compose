package com.greencom.android.podcasts2.ui.screens.app

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.greencom.android.podcasts2.ui.common.Screen
import com.greencom.android.podcasts2.ui.screens.app.components.BottomNavBarItem
import com.greencom.android.podcasts2.ui.screens.discover.DiscoverScreen

fun NavGraphBuilder.discoverNavGraph() {
    navigation(route = BottomNavBarItem.Discover.route, startDestination = Screen.Discover.route) {
        composable(Screen.Discover.route) { DiscoverScreen() }
    }
}
