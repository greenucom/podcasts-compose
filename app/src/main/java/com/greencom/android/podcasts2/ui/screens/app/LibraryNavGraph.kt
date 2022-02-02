package com.greencom.android.podcasts2.ui.screens.app

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.greencom.android.podcasts2.ui.screens.app.components.BottomNavBarItem
import com.greencom.android.podcasts2.ui.screens.library.LibraryScreen

fun NavGraphBuilder.libraryNavGraph() {
    navigation(route = BottomNavBarItem.Library.route, startDestination = Screen.Library.route) {
        composable(Screen.Library.route) { LibraryScreen() }
    }
}
