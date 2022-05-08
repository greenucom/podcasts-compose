package com.greencom.android.podcasts2.ui.common.screenbehavior

import androidx.compose.runtime.Immutable
import com.greencom.android.podcasts2.ui.common.BottomNavBarState
import com.greencom.android.podcasts2.ui.navigation.BottomNavBarItem

typealias OnBottomNavBarItemReselected = (BottomNavBarItem) -> Boolean

@Immutable
data class ScreenBehavior(
    val bottomNavBarState: BottomNavBarState = BottomNavBarState.Visible,
    val onBottomNavBarItemReselected: OnBottomNavBarItemReselected? = null,
) {

    companion object {
        val Default: ScreenBehavior = ScreenBehavior()
    }

}

class ScreenBehaviorBuilder {

    var bottomNavBarState: BottomNavBarState = ScreenBehavior.Default.bottomNavBarState
    var onBottomNavBarItemReselected: OnBottomNavBarItemReselected? =
        ScreenBehavior.Default.onBottomNavBarItemReselected

    fun build(): ScreenBehavior = ScreenBehavior(
        bottomNavBarState = bottomNavBarState,
        onBottomNavBarItemReselected = onBottomNavBarItemReselected,
    )

}

fun buildScreenBehavior(builderAction: ScreenBehaviorBuilder.() -> Unit): ScreenBehavior {
    return ScreenBehaviorBuilder().apply { builderAction() }.build()
}
