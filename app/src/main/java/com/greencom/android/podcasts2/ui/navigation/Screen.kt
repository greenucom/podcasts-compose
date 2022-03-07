package com.greencom.android.podcasts2.ui.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Screen(val route: String) {

    object Home : Screen("home")
    object Discover : Screen("discover")
    object Library : Screen("library")

    object Search : Screen("search")

    object Podcast : Screen("podcasts/{podcastId}") {
        const val PodcastId = "podcastId"

        fun createRoute(podcastId: Long): String = "podcasts/$podcastId"

        val arguments: List<NamedNavArgument>
            get() = listOf(
                navArgument(PodcastId) { type = NavType.LongType },
            )
    }

}
