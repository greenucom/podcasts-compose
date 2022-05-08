package com.greencom.android.podcasts2.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Apps
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.VideoLibrary
import androidx.compose.ui.graphics.vector.ImageVector
import com.greencom.android.podcasts2.R

sealed class BottomNavBarItem(
    val route: String,
    @StringRes val label: Int,
    val icon: ImageVector,
) {

    object MyPodcasts : BottomNavBarItem(
        route = "myPodcastsGraph",
        label = R.string.podcasts,
        icon = Icons.Outlined.Apps,
    )

    object Discover : BottomNavBarItem(
        route = "discoverGraph",
        label = R.string.discover,
        icon = Icons.Outlined.Search,
    )

    object Library : BottomNavBarItem(
        route = "libraryGraph",
        label = R.string.library,
        icon = Icons.Outlined.VideoLibrary,
    )

    object Profile : BottomNavBarItem(
        route = "profileGraph",
        label = R.string.profile,
        icon = Icons.Outlined.PersonOutline,
    )

    companion object {
        val items = listOf(MyPodcasts, Discover, Library, Profile)
    }

}
