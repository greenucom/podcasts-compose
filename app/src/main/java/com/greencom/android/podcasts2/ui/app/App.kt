package com.greencom.android.podcasts2.ui.app

import androidx.compose.animation.*
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.greencom.android.podcasts2.ui.app.component.PodcastsBottomNavigation
import com.greencom.android.podcasts2.ui.app.component.PodcastsNavigationRail
import com.greencom.android.podcasts2.ui.common.LocalContentBottomPadding
import com.greencom.android.podcasts2.ui.common.copy
import com.greencom.android.podcasts2.ui.common.screenbehavior.LocalScreenBehaviorController
import com.greencom.android.podcasts2.ui.common.screenbehavior.NavigationBarState
import com.greencom.android.podcasts2.ui.navigation.NavigationItem
import com.greencom.android.podcasts2.ui.navigation.discoverNavGraph
import com.greencom.android.podcasts2.ui.navigation.libraryNavGraph
import com.greencom.android.podcasts2.ui.navigation.podcastsNavGraph

private val BottomNavigationHeight = 56.dp

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

        val contentBottomPadding = getContentBottomPadding()
        CompositionLocalProvider(LocalContentBottomPadding provides contentBottomPadding) {

            if (windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact) {
                PodcastsNavHost(
                    // Let the content be overlapped by the bottom navigation bar
                    modifier = Modifier.padding(paddingValues.copy(bottom = 0.dp)),
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
}

@Composable
fun BottomNavigationRespectingWindowInsets(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    val currentScreenBehavior =
        LocalScreenBehaviorController.current?.currentScreenBehaviorAsState?.value

    val isBottomNavigationVisible =
        currentScreenBehavior?.navigationBarState !is NavigationBarState.Gone
    val animateVisibility =
        currentScreenBehavior?.navigationBarState?.animateTransition == true

    AnimatedVisibility(
        modifier = modifier,
        visible = isBottomNavigationVisible,
        enter = if (animateVisibility) slideInVertically { it } else EnterTransition.None,
        exit = if (animateVisibility) slideOutVertically { it } else ExitTransition.None,
    ) {
        Column {
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

@Composable
private fun getContentBottomPadding(): Dp {
    val currentScreenBehavior = LocalScreenBehaviorController.current
    val navigationBarState =
        currentScreenBehavior?.currentScreenBehaviorAsState?.value?.navigationBarState

    val systemNavigationBarHeight =
        WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
    val bottomNavigationHeight = if (navigationBarState is NavigationBarState.Visible) {
        BottomNavigationHeight
    } else {
        0.dp
    }

    val totalHeight = systemNavigationBarHeight + bottomNavigationHeight
    return animateDpAsState(totalHeight).value
}
