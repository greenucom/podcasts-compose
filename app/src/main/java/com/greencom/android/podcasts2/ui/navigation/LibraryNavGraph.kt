package com.greencom.android.podcasts2.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.greencom.android.podcasts2.ui.common.ScreenBehavior
import com.greencom.android.podcasts2.ui.screen.library.LibraryScreen

fun NavGraphBuilder.libraryNavGraph(
    navController: NavHostController,
    onScreenBehaviorChanged: (ScreenBehavior) -> Unit,
) {
    navigation(
        route = BottomNavBarItem.Library.route,
        startDestination = Screen.Library.route,
    ) {

        composable(Screen.Library.route) {
            LibraryScreen()
        }

    }
}
