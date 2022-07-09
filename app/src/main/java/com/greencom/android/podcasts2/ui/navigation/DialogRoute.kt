package com.greencom.android.podcasts2.ui.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class DialogRoute<Args>(routeSchema: String) : Route<Args>(routeSchema) {

    object PodcastDescription : DialogRoute<PodcastDescription.Args>(
        routeSchema = "podcastDescription/{podcastId}",
    ) {
        const val ArgPodcastId = "podcastId"

        override fun createRoute(args: Args): String {
            val (podcastId) = args
            return "podcastDescription/$podcastId"
        }

        override val arguments: List<NamedNavArgument>
            get() = listOf(navArgument(name = ArgPodcastId) { type = NavType.LongType })

        data class Args(val podcastId: Long)
    }

}
