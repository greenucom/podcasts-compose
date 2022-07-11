package com.greencom.android.podcasts2.ui.common.screenbehavior

import android.os.Parcelable
import androidx.compose.runtime.Stable
import kotlinx.parcelize.Parcelize

@Parcelize
@Stable
sealed class NavigationBarState(open val animateTransition: Boolean) : Parcelable {

    data class Visible(
        override val animateTransition: Boolean = true
    ) : NavigationBarState(animateTransition)

    data class Gone(
        override val animateTransition: Boolean = true
    ) : NavigationBarState(animateTransition)

}
