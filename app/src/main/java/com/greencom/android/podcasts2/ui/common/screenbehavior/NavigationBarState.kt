package com.greencom.android.podcasts2.ui.common.screenbehavior

import androidx.compose.runtime.Immutable

@Immutable
sealed class NavigationBarState(open val animateTransition: Boolean) {

    @Immutable
    data class Visible(
        override val animateTransition: Boolean = true
    ) : NavigationBarState(animateTransition)

    @Immutable
    data class Gone(
        override val animateTransition: Boolean = true
    ) : NavigationBarState(animateTransition)

}
