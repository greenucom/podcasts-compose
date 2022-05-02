package com.greencom.android.podcasts2.ui.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.greencom.android.podcasts2.domain.podcast.Podcast
import com.greencom.android.podcasts2.ui.common.requireLong
import com.greencom.android.podcasts2.ui.screen.discover.DiscoverScreen
import com.greencom.android.podcasts2.ui.screen.podcast.PodcastScreen
import com.greencom.android.podcasts2.ui.screen.podcast.PodcastViewModel
import com.greencom.android.podcasts2.ui.screen.search.SearchScreen

fun NavGraphBuilder.discoverNavGraph(
    navController: NavHostController,
) {
    val navigateUp: () -> Unit = {
        navController.navigateUp()
    }

    val navigateToPodcastScreen = { podcast: Podcast ->
        val route = Screen.Podcast.createRoute(podcast.id)
        navController.navigate(route)
    }

    navigation(
        route = BottomNavBarItem.Discover.route,
        startDestination = Screen.Discover.route,
    ) {

        composable(Screen.Discover.route) {
            DiscoverScreen(
                onPodcastClicked = navigateToPodcastScreen,
                onSearchClicked = { navController.navigate(Screen.Search.route) },
            )
        }

        composable(Screen.Search.route) {
            SearchScreen(onPodcastClicked = navigateToPodcastScreen)
        }

        composable(
            route = Screen.Podcast.route,
            arguments = Screen.Podcast.arguments,
        ) { backStackEntry ->
            val podcastId = backStackEntry.requireLong(Screen.Podcast.PodcastId)
            val podcastViewModel = hiltViewModel<PodcastViewModel>()
            podcastViewModel.setParameters(podcastId)

            PodcastScreen(
                onBackClicked = navigateUp,
                viewModel = podcastViewModel,
            )
        }

    }
}
