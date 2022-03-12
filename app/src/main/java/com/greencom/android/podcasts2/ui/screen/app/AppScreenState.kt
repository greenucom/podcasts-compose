package com.greencom.android.podcasts2.ui.screen.app

import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.greencom.android.podcasts2.ui.navigation.Screen

class AppScreenState(
    val scaffoldState: ScaffoldState,
    val navController: NavHostController,
) {

    private val routesWithBottomNavBar = setOf(
        Screen.MyPodcasts.route, Screen.Discover.route, Screen.Library.route, Screen.Profile.route
    )

    fun shouldBottomNavBarBeVisible(route: String?): Boolean {
        return route in routesWithBottomNavBar
    }

}

@Composable
fun rememberAppScreenState(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    navController: NavHostController = rememberNavController(),
) = remember(scaffoldState, navController) {
    AppScreenState(
        scaffoldState = scaffoldState,
        navController = navController,
    )
}
