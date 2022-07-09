package com.greencom.android.podcasts2.ui.navigation

sealed class DialogRoute<Args>(routeSchema: String) : Route<Args>(routeSchema) {

    object PodcastDescription : DialogRoute<PodcastDescription.Args>(
        routeSchema = "podcastDescription/{podcastId}"
    ) {
        override fun createRoute(args: Args): String {
            val (podcastId) = args
            return "podcastDescription/$podcastId"
        }

        data class Args(val podcastId: Long)
    }

}
