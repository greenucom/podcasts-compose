package com.greencom.android.podcasts2.ui.navigation

import androidx.compose.runtime.MutableState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.greencom.android.podcasts2.ui.common.BottomNavBarState

fun NavGraphBuilder.profileNavGraph(
    navController: NavHostController,
    bottomNavBarState: MutableState<BottomNavBarState>,
) {
    navigation(
        route = BottomNavBarItem.Profile.route,
        startDestination = Screen.Profile.route,
    ) {

        composable(Screen.Profile.route) {  }

    }
}
