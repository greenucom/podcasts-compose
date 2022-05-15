package com.greencom.android.podcasts2.ui.screen.app.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.greencom.android.podcasts2.ui.navigation.NavigationItem

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = NavigationItem.Podcasts.route,
    ) {
        composable(NavigationItem.Podcasts.route) {}

        composable(NavigationItem.Discover.route) {}

        composable(NavigationItem.Library.route) {}
    }
}
