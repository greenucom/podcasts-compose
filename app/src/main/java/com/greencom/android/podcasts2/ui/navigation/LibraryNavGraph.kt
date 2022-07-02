package com.greencom.android.podcasts2.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation

fun NavGraphBuilder.libraryNavGraph(navController: NavHostController) {
    navigation(
        route = NavigationItem.Library.route,
        startDestination = Route.Library.routeSchema,
    ) {

        composable(route = Route.Library.routeSchema) {

        }

    }
}
