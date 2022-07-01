package com.greencom.android.podcasts2.ui.common

import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.greencom.android.podcasts2.ui.navigation.NavigationItem

fun NavHostController.navigateToNavigationItem(item: NavigationItem) {
    navigate(item.route) {
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}

fun NavHostController.popBackStackToSelectedNavigationItemStartDestination() {
    val selectedItemStartDestination = this.findSelectedNavigationItemStartDestination()
    popBackStack(
        destinationId = selectedItemStartDestination.id,
        inclusive = false,
    )
}

fun NavHostController.findSelectedNavigationItemStartDestination(): NavDestination {
    val topmostGraph = this.graph
    var currentGraph = this.currentDestination?.parent
    while (currentGraph?.parent != topmostGraph) {
        currentGraph = currentGraph?.parent
    }
    return currentGraph.findStartDestination()
}

fun NavBackStackEntry.requireString(key: String): String {
    return requireNotNull(this.arguments?.getString(key)) { "$key nav argument is null" }
}

fun NavBackStackEntry.requireInt(key: String): Int {
    return requireNotNull(this.arguments?.getInt(key)) { "$key nav argument is null" }
}

fun NavBackStackEntry.requireLong(key: String): Long {
    return requireNotNull(this.arguments?.getLong(key)) { "$key nav argument is null" }
}
