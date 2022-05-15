package com.greencom.android.podcasts2.ui.screen.app

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.greencom.android.podcasts2.ui.navigation.NavigationItem
import com.greencom.android.podcasts2.ui.screen.app.component.NavigationBarCustomWithSystemBars

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScreen(
    windowSizeClass: WindowSizeClass,
    modifier: Modifier = Modifier,
) {
    val screenState = rememberAppScreenState()

    Scaffold(
        modifier = modifier,
        bottomBar = {
            if (windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact) {
                NavigationBarCustomWithSystemBars(screenState.navController)
            }
        },
    ) {
        NavHost(
            navController = screenState.navController,
            startDestination = NavigationItem.Podcasts.route,
        ) {
            composable(NavigationItem.Podcasts.route) {}

            composable(NavigationItem.Discover.route) {}

            composable(NavigationItem.Library.route) {}
        }
    }
}
