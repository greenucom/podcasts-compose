package com.greencom.android.podcasts2.ui.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.greencom.android.podcasts2.ui.common.requireLong
import com.greencom.android.podcasts2.ui.screen.app.AppViewModel
import com.greencom.android.podcasts2.ui.screen.discover.DiscoverScreen
import com.greencom.android.podcasts2.ui.screen.podcast.PodcastScreen
import com.greencom.android.podcasts2.ui.screen.podcast.PodcastViewModel
import com.greencom.android.podcasts2.ui.screen.search.SearchScreen

fun NavGraphBuilder.discoverNavGraph(
    navController: NavHostController,
    appViewModel: AppViewModel,
) {
    navigation(
        route = BottomNavBarItem.Discover.route,
        startDestination = Screen.Discover.route,
    ) {

        composable(Screen.Discover.route) {
            DiscoverScreen(
                onPodcastClicked = { podcast ->
                    navController.navigate(Screen.Podcast.createRoute(podcast.id))
                },
                onSearchClicked = { navController.navigate(Screen.Search.route) },
                appViewModel = appViewModel,
            )
        }

        composable(Screen.Search.route) {
            SearchScreen()
        }

        composable(
            route = Screen.Podcast.route,
            arguments = Screen.Podcast.arguments,
        ) { backStackEntry ->
            val podcastId = backStackEntry.requireLong(Screen.Podcast.PodcastId)
            val podcastViewModel = hiltViewModel<PodcastViewModel>()
            podcastViewModel.setParameters(podcastId)
            PodcastScreen(podcastViewModel = podcastViewModel)
        }

    }
}
