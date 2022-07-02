package com.greencom.android.podcasts2.ui.navigation

sealed class Route<Args>(val routeSchema: String) {

    abstract fun createRoute(args: Args): String

    object Podcasts : Route<Unit>(routeSchema = "podcasts") {
        override fun createRoute(args: Unit): String = routeSchema
    }

    object Discover : Route<Unit>(routeSchema = "discover") {
        override fun createRoute(args: Unit): String = routeSchema
    }

    object Library : Route<Unit>(routeSchema = "library") {
        override fun createRoute(args: Unit): String = routeSchema
    }

}
