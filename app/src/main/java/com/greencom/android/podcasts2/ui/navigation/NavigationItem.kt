package com.greencom.android.podcasts2.ui.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.greencom.android.podcasts2.R
import com.greencom.android.podcasts2.ui.common.PodcastsIcons

val NavigationItems = listOf(
    NavigationItem.Podcasts,
    NavigationItem.Discover,
    NavigationItem.Library,
)

sealed class NavigationItem(
    val route: String,
    @StringRes val labelResId: Int,
    @DrawableRes val iconSelectedResId: Int,
    @DrawableRes val iconUnselectedResId: Int,
) {

    object Podcasts : NavigationItem(
        route = "PodcastsGraph",
        labelResId = R.string.podcasts,
        iconSelectedResId = PodcastsIcons.PodcastsFilled,
        iconUnselectedResId = PodcastsIcons.PodcastsOutlined,
    )

    object Discover : NavigationItem(
        route = "DiscoverGraph",
        labelResId = R.string.discover,
        iconSelectedResId = PodcastsIcons.DiscoverFilled,
        iconUnselectedResId = PodcastsIcons.DiscoverOutlined,
    )

    object Library : NavigationItem(
        route = "LibraryGraph",
        labelResId = R.string.library,
        iconSelectedResId = PodcastsIcons.LibraryFilled,
        iconUnselectedResId = PodcastsIcons.LibraryOutlined,
    )

}
