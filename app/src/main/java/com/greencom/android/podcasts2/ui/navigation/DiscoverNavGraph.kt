package com.greencom.android.podcasts2.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.navigation
import com.greencom.android.podcasts2.ui.route.discover.DiscoverRoute

fun NavGraphBuilder.discoverNavGraph(navController: NavHostController) {
    navigation(
        route = NavigationItem.Discover.route,
        startDestination = Route.Discover.routeSchema,
    ) {

        composable(route = Route.Discover.routeSchema) {
            DiscoverRoute(
                onSearchPodcastsClicked = { /* TODO: Open search */ },
                onPodcastLongClicked = {
                    val args = DialogRoute.PodcastDescription.Args(podcastId = it.id)
                    val route = DialogRoute.PodcastDescription.createRoute(args)
                    navController.navigate(route)
                },
            )
        }

        dialog(
            route = DialogRoute.PodcastDescription.routeSchema,
            arguments = DialogRoute.PodcastDescription.arguments,
        ) {
            // TODO: Create dialog
        }

    }
}
