package com.greencom.podcasts2.ui.screens.app

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.greencom.podcasts2.ui.screens.app.components.BottomNavBar
import com.greencom.podcasts2.ui.screens.discover.DiscoverScreen
import com.greencom.podcasts2.ui.screens.home.HomeScreen
import com.greencom.podcasts2.ui.screens.library.LibraryScreen
import com.greencom.podcasts2.ui.theme.PodcastsComposeTheme

@Composable
fun AppScreen(modifier: Modifier = Modifier) {
    val appScreenState = rememberAppScreenState()

    Scaffold(
        modifier = modifier,
        scaffoldState = appScreenState.scaffoldState,
        bottomBar = { BottomNavBar(navController = appScreenState.navController) },
    ) { innerPadding ->
        NavHost(
            modifier = Modifier.padding(innerPadding),
            navController = appScreenState.navController,
            startDestination = BottomNavItem.Home.route,
        ) {
            composable(BottomNavItem.Home.route) { HomeScreen() }
            composable(BottomNavItem.Discover.route) { DiscoverScreen() }
            composable(BottomNavItem.Library.route) { LibraryScreen() }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Light() {
    PodcastsComposeTheme {
        AppScreen()
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, locale = "ru")
@Composable
private fun Dark() {
    PodcastsComposeTheme {
        AppScreen()
    }
}
