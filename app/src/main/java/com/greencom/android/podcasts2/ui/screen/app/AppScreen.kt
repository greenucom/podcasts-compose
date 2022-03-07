package com.greencom.android.podcasts2.ui.screen.app

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import com.greencom.android.podcasts2.ui.navigation.BottomNavBarItem
import com.greencom.android.podcasts2.ui.navigation.discoverNavGraph
import com.greencom.android.podcasts2.ui.navigation.homeNavGraph
import com.greencom.android.podcasts2.ui.navigation.libraryNavGraph
import com.greencom.android.podcasts2.ui.screen.app.component.BottomNavBar
import com.greencom.android.podcasts2.ui.theme.PodcastsComposeTheme

@Composable
fun AppScreen(modifier: Modifier = Modifier) {
    val screenState = rememberAppScreenState()

    Scaffold(
        modifier = modifier,
        scaffoldState = screenState.scaffoldState,
        bottomBar = { BottomNavBar(navController = screenState.navController) },
    ) { paddings ->
        NavHost(
            modifier = Modifier.padding(paddings),
            navController = screenState.navController,
            startDestination = BottomNavBarItem.Home.route,
        ) {
            homeNavGraph(screenState.navController)
            discoverNavGraph(screenState.navController)
            libraryNavGraph(screenState.navController)
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
