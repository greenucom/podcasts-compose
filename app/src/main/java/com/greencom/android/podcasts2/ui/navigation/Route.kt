package com.greencom.android.podcasts2.ui.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavDeepLink
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Route<P>(val routeSchema: String) {

    abstract fun createRoute(args: P): String

    open val arguments: List<NamedNavArgument>
        get() = emptyList()

    open val deepLinks: List<NavDeepLink>
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

    object Podcast : Route<Podcast.Args>(routeSchema = "podcast/{podcastId}") {
        override fun createRoute(args: Args): String {
            val (podcastId) = args
            return "podcast/$podcastId"
        }

        override val arguments: List<NamedNavArgument>
            get() = listOf(navArgument("podcastId") { type = NavType.LongType })

        data class Args(val podcastId: Long)
    }

}
