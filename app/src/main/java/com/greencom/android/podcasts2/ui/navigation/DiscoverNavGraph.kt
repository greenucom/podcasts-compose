package com.greencom.android.podcasts2.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import com.greencom.android.podcasts2.ui.common.composableRoute
import com.greencom.android.podcasts2.ui.route.discover.DiscoverRoute
import com.greencom.android.podcasts2.ui.route.podcast.PodcastRoute
import com.greencom.android.podcasts2.ui.route.search.SearchRoute

fun NavGraphBuilder.discoverNavGraph(navController: NavHostController) {
    navigation(
        route = NavigationItem.Discover.route,
        startDestination = Route.Discover.routeSchema,
    ) {

        composableRoute(Route.Discover) {
            DiscoverRoute(
                navigateToSearchRoute = {
                    val route = Route.Search.createRoute(Unit)
                    navController.navigate(route)
                },
                navigateToPodcastRoute = {
                    val route = Route.Podcast.createRoute(it)
                    navController.navigate(route)
                }
            )
        }

        composableRoute(Route.Search) {
            SearchRoute()
        }

        composableRoute(Route.Podcast) {
            PodcastRoute()
        }
    }
}
