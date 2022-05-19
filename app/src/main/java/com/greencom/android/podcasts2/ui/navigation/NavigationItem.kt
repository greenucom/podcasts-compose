package com.greencom.android.podcasts2.ui.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.greencom.android.podcasts2.R
import com.greencom.android.podcasts2.ui.common.PodcastsIcons

val NavigationItems = listOf(
    NavigationItem.MyPodcasts,
    NavigationItem.Discover,
    NavigationItem.Library,
)

sealed class NavigationItem(
    @StringRes val labelResId: Int,
    @DrawableRes val iconSelectedResId: Int,
    @DrawableRes val iconUnselectedResId: Int,
    val route: String,
) {

    object MyPodcasts : NavigationItem(
        labelResId = R.string.my_podcasts,
        iconSelectedResId = PodcastsIcons.GridViewFilled,
        iconUnselectedResId = PodcastsIcons.GridViewNotFilled,
        route = "MyPodcastsGraph",
    )

    object Discover : NavigationItem(
        labelResId = R.string.discover,
        iconSelectedResId = PodcastsIcons.AssistantFilled,
        iconUnselectedResId = PodcastsIcons.AssistantNotFilled,
        route = "DiscoverGraph",
    )

    object Library : NavigationItem(
        labelResId = R.string.library,
        iconSelectedResId = PodcastsIcons.LibraryMusicFilled,
        iconUnselectedResId = PodcastsIcons.LibraryMusicNotFilled,
        route = "LibraryGraph",
    )

}