package com.greencom.android.podcasts2.ui.screens.app.components

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.VideoLibrary
import androidx.compose.ui.graphics.vector.ImageVector
import com.greencom.android.podcasts2.R
import com.greencom.android.podcasts2.ui.Screen

sealed class BottomNavBarItem(
    val route: String,
    @StringRes val labelResId: Int,
    val icon: ImageVector
) {

    object Home : BottomNavBarItem(Screen.Home.route, R.string.home, Icons.Outlined.Home)
    object Discover : BottomNavBarItem(Screen.Discover.route, R.string.discover, Icons.Outlined.Search)
    object Library : BottomNavBarItem(Screen.Library.route, R.string.library, Icons.Outlined.VideoLibrary)

    companion object {
        val items = listOf(Home, Discover, Library)
    }

}
