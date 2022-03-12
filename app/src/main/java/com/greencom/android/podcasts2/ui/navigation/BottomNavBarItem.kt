package com.greencom.android.podcasts2.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Apps
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.VideoLibrary
import androidx.compose.ui.graphics.vector.ImageVector
import com.greencom.android.podcasts2.R

sealed class BottomNavBarItem(
    val route: String,
    @StringRes val labelResId: Int,
    val icon: ImageVector,
) {

    object MyPodcasts : BottomNavBarItem(
        route = "myPodcastsGraph",
        labelResId = R.string.podcasts,
        icon = Icons.Outlined.Apps,
    )

    object Discover : BottomNavBarItem(
        route = "discoverGraph",
        labelResId = R.string.discover,
        icon = Icons.Outlined.Search,
    )

    object Library : BottomNavBarItem(
        route = "libraryGraph",
        labelResId = R.string.library,
        icon = Icons.Outlined.VideoLibrary,
    )

    companion object {
        val items = listOf(MyPodcasts, Discover, Library)
    }

}
