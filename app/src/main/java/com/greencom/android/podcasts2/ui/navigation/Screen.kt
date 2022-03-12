package com.greencom.android.podcasts2.ui.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Screen(val route: String) {

    object MyPodcasts : Screen("myPodcasts")
    object Discover : Screen("discover")
    object Library : Screen("library")
    object Profile : Screen("profile")

    object Search : Screen("search")

    object Podcast : Screen("podcasts/{podcastId}") {
        const val PodcastId = "podcastId"

        fun createRoute(podcastId: Long): String = "podcasts/$podcastId"

        val arguments: List<NamedNavArgument>
            get() = listOf(navArgument(PodcastId) { type = NavType.LongType })
    }

}
