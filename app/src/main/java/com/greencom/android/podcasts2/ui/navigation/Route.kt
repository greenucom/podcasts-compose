package com.greencom.android.podcasts2.ui.navigation

import androidx.navigation.NamedNavArgument

sealed class Route<Args>(val routeSchema: String) {

    abstract fun createRoute(args: Args): String

    open val arguments: List<NamedNavArgument>
        get() = emptyList()

    object Podcasts : Route<Unit>(routeSchema = "podcasts") {
        override fun createRoute(args: Unit): String = routeSchema
    }

    object Discover : Route<Unit>(routeSchema = "discover") {
        override fun createRoute(args: Unit): String = routeSchema
    }

    object Library : Route<Unit>(routeSchema = "library") {
        override fun createRoute(args: Unit): String = routeSchema
    }

    object Search : Route<Unit>(routeSchema = "search") {
        override fun createRoute(args: Unit): String = routeSchema
    }

}
