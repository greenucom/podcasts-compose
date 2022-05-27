package com.greencom.android.podcasts2.ui.common.screenbehavior

import androidx.compose.runtime.Stable

@Stable
sealed class NavigationBarState(open val animateTransition: Boolean) {

    data class Visible(
        override val animateTransition: Boolean = true
    ) : NavigationBarState(animateTransition)

    data class Gone(
        override val animateTransition: Boolean = true
    ) : NavigationBarState(animateTransition)

}
