package com.greencom.android.podcasts2.ui.screen.app.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.greencom.android.podcasts2.ui.navigation.NavigationItem
import com.greencom.android.podcasts2.ui.navigation.discoverNavGraph
import com.greencom.android.podcasts2.ui.navigation.libraryNavGraph
import com.greencom.android.podcasts2.ui.navigation.myPodcastsNavGraph

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = NavigationItem.MyPodcasts.route,
    ) {

        myPodcastsNavGraph(navController = navController)

        discoverNavGraph(navController = navController)

        libraryNavGraph(navController = navController)

    }
}
