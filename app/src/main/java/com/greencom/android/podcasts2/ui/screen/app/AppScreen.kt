package com.greencom.android.podcasts2.ui.screen.app

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.greencom.android.podcasts2.ui.navigation.NavigationItem
import com.greencom.android.podcasts2.ui.navigation.discoverNavGraph
import com.greencom.android.podcasts2.ui.navigation.libraryNavGraph
import com.greencom.android.podcasts2.ui.navigation.podcastsNavGraph
import com.greencom.android.podcasts2.ui.screen.app.component.PodcastsBottomNavigation
import com.greencom.android.podcasts2.ui.screen.app.component.PodcastsNavigationRail

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
                BottomNavigationRespectingWindowInsets(navController = screenState.navController)
            }
        },
    ) { paddingValues ->

        if (windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact) {
            PodcastsNavHost(
                modifier = Modifier.padding(paddingValues),
                navController = screenState.navController,
            )
        } else {
            Row(modifier = Modifier.padding(paddingValues)) {
                NavigationRailRespectingWindowInsets(navController = screenState.navController)
                PodcastsNavHost(navController = screenState.navController)
            }
        }
    }
}

@Composable
fun BottomNavigationRespectingWindowInsets(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        PodcastsBottomNavigation(navController = navController)

        val spacerColor = MaterialTheme.colors.surface
        Spacer(
            modifier = Modifier
                .windowInsetsBottomHeight(WindowInsets.navigationBars)
                .fillMaxWidth()
                .drawBehind { drawRect(color = spacerColor) }
        )
    }
}

@Composable
fun NavigationRailRespectingWindowInsets(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .windowInsetsPadding(WindowInsets.displayCutout.only(WindowInsetsSides.Start))
            .windowInsetsPadding(WindowInsets.navigationBars.only(WindowInsetsSides.Start)),
    ) {
        Spacer(modifier = Modifier.windowInsetsTopHeight(WindowInsets.statusBars))
        PodcastsNavigationRail(navController = navController)
        Spacer(modifier = Modifier.windowInsetsBottomHeight(WindowInsets.navigationBars))
    }
}

@Composable
fun PodcastsNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = NavigationItem.Podcasts.route,
    ) {

        podcastsNavGraph(navController = navController)

        discoverNavGraph(navController = navController)

        libraryNavGraph(navController = navController)
    }
}
