package com.greencom.android.podcasts2.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
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
                onPodcastLongClicked = { /* TODO: Open podcast description dialog */ },
            )
        }

    }
}
