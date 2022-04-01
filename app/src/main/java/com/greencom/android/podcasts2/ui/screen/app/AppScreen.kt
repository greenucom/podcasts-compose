package com.greencom.android.podcasts2.ui.screen.app

import android.content.res.Configuration
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import com.greencom.android.podcasts2.ui.common.ContentBottomPadding
import com.greencom.android.podcasts2.ui.common.LocalContentBottomPadding
import com.greencom.android.podcasts2.ui.common.copy
import com.greencom.android.podcasts2.ui.navigation.*
import com.greencom.android.podcasts2.ui.screen.app.component.BottomNavBar
import com.greencom.android.podcasts2.ui.theme.PodcastsComposeTheme

@Composable
fun AppScreen(
    modifier: Modifier = Modifier,
    appViewModel: AppViewModel = hiltViewModel(),
) {
    val screenState = rememberAppScreenState()

    val navBackStackEntry by screenState.navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val isBottomNavBarVisible = screenState.isBottomNavBarVisible(currentRoute)
    val bottomNavBarAnimSpringStiffness = Spring.StiffnessMediumLow

    Scaffold(
        modifier = modifier,
        scaffoldState = screenState.scaffoldState,
        bottomBar = {
            BottomNavBar(
                navController = screenState.navController,
                currentNavBackStackEntry = navBackStackEntry,
                isVisible = isBottomNavBarVisible,
                onItemReselected = appViewModel::onBottomNavBarItemReselected,
                springStiffness = bottomNavBarAnimSpringStiffness,
            )
        },
    ) { paddingValues ->

        val bottomNavBarHeight = if (isBottomNavBarVisible) {
            paddingValues.calculateBottomPadding()
        } else {
            0.dp
        }
        val contentBottomPadding by animateDpAsState(
            targetValue = bottomNavBarHeight,
            animationSpec = spring(stiffness = bottomNavBarAnimSpringStiffness),
        )

        // Remove bottom padding to allow NavHost content to be placed behind bottom nav bar
        val navHostPaddingValues = paddingValues.copy(bottom = 0.dp)

        CompositionLocalProvider(
            LocalContentBottomPadding provides ContentBottomPadding(contentBottomPadding)
        ) {
            NavHost(
                modifier = Modifier.padding(navHostPaddingValues),
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
