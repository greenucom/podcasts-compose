package com.greencom.android.podcasts2.ui.common

import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

fun NavHostController.findBottomNavBarItemStartDestination(): NavDestination {
    val topMostGraph = this.graph
    var currentGraph = this.currentDestination?.parent
    while (currentGraph?.parent != topMostGraph) {
        currentGraph = currentGraph?.parent
    }
    return currentGraph.findStartDestination()
}

fun NavBackStackEntry.requireString(key: String): String {
    return requireNotNull(this.arguments?.getString(key))
}

fun NavBackStackEntry.requireInt(key: String): Int {
    return requireNotNull(this.arguments?.getInt(key))
}

fun NavBackStackEntry.requireLong(key: String): Long {
    return requireNotNull(this.arguments?.getLong(key))
}
