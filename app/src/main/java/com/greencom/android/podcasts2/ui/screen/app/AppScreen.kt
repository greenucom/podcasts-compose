package com.greencom.android.podcasts2.ui.screen.app

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import com.greencom.android.podcasts2.ui.navigation.*
import com.greencom.android.podcasts2.ui.screen.app.component.BottomNavBar
import com.greencom.android.podcasts2.ui.theme.PodcastsComposeTheme

@Composable
fun AppScreen(
    modifier: Modifier = Modifier,
    appViewModel: AppViewModel = hiltViewModel(),
) {
    val screenState = rememberAppScreenState()

    Scaffold(
        modifier = modifier,
        scaffoldState = screenState.scaffoldState,
        bottomBar = {
            val navBackStackEntry by screenState.navController.currentBackStackEntryAsState()
            val currentDestinationRoute = navBackStackEntry?.destination?.route

            AnimatedVisibility(
                visible = screenState.shouldBottomNavBarBeVisible(currentDestinationRoute),
                enter = slideInVertically(initialOffsetY = { it }),
                exit = slideOutVertically(targetOffsetY = { it }),
            ) {
                BottomNavBar(
                    navController = screenState.navController,
                    onItemReselected = appViewModel::onBottomNavBarItemReselected,
                )
            }
        },
    ) { paddingValues ->

        NavHost(
            modifier = Modifier.padding(paddingValues),
            navController = screenState.navController,
            startDestination = BottomNavBarItem.MyPodcasts.route,
        ) {
            myPodcastsNavGraph(screenState.navController)

            discoverNavGraph(
                navController = screenState.navController,
                appViewModel = appViewModel,
            )

            libraryNavGraph(screenState.navController)

            profileNavGraph(screenState.navController)
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun Light() {
    PodcastsComposeTheme {
        AppScreen()
    }
}

@Composable
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    locale = "ru",
)
private fun Dark() {
    PodcastsComposeTheme {
        AppScreen()
    }
}
