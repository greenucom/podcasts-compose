package com.greencom.android.podcasts2.ui.screens.app

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import com.greencom.android.podcasts2.ui.screens.app.components.BottomNavBar
import com.greencom.android.podcasts2.ui.navigation.BottomNavBarItem
import com.greencom.android.podcasts2.ui.navigation.discoverNavGraph
import com.greencom.android.podcasts2.ui.navigation.homeNavGraph
import com.greencom.android.podcasts2.ui.navigation.libraryNavGraph
import com.greencom.android.podcasts2.ui.theme.PodcastsComposeTheme

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
            startDestination = BottomNavBarItem.Home.route,
        ) {
            homeNavGraph()
            discoverNavGraph()
            libraryNavGraph()
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
