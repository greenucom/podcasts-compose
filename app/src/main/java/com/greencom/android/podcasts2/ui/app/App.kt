package com.greencom.android.podcasts2.ui.app

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
import com.greencom.android.podcasts2.ui.app.component.PodcastsBottomNavigation
import com.greencom.android.podcasts2.ui.app.component.PodcastsNavigationRail
import com.greencom.android.podcasts2.ui.navigation.NavigationItem
import com.greencom.android.podcasts2.ui.navigation.discoverNavGraph
import com.greencom.android.podcasts2.ui.navigation.libraryNavGraph
import com.greencom.android.podcasts2.ui.navigation.podcastsNavGraph

@Composable
fun App(
    windowSizeClass: WindowSizeClass,
    modifier: Modifier = Modifier,
) {
    val appState = rememberAppState()

    Scaffold(
        modifier = modifier,
        bottomBar = {
            if (windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact) {
                BottomNavigationRespectingWindowInsets(navController = appState.navController)
            }
        },
    ) { paddingValues ->

        if (windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact) {
            PodcastsNavHost(
                modifier = Modifier.padding(paddingValues),
                navController = appState.navController,
            )
        } else {
            Row(modifier = Modifier.padding(paddingValues)) {
                NavigationRailRespectingWindowInsets(navController = appState.navController)
                PodcastsNavHost(navController = appState.navController)
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
    PodcastsNavigationRail(
        modifier = modifier
            .windowInsetsPadding(WindowInsets.displayCutout.only(WindowInsetsSides.Start))
            .windowInsetsPadding(WindowInsets.statusBars.only(WindowInsetsSides.Top))
            .windowInsetsPadding(WindowInsets.navigationBars.only(WindowInsetsSides.Start)),
        navController = navController,
    )
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
