package com.greencom.podcasts2.ui.screens.app

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.VideoLibrary
import androidx.compose.ui.graphics.vector.ImageVector
import com.greencom.podcasts2.R

sealed class BottomNavItem(
    val route: String,
    @StringRes val labelResId: Int,
    val icon: ImageVector
) {

    object Home : BottomNavItem("home", R.string.home, Icons.Outlined.Home)
    object Discover : BottomNavItem("discover", R.string.discover, Icons.Outlined.Search)
    object Library : BottomNavItem("library", R.string.library, Icons.Outlined.VideoLibrary)

    companion object {
        val items = listOf(Home, Discover, Library)
    }

}
