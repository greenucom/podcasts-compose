package com.greencom.android.podcasts2.ui.common.screenbehavior

import androidx.compose.runtime.Immutable

@Immutable
data class ScreenBehavior(
    val bottomNavigationState: NavigationBarState = NavigationBarState.Visible(),
    val navigationRailState: NavigationBarState = NavigationBarState.Visible(),
    val onNavigationItemReselected: OnNavigationItemReselected? = null,
) {

    companion object {
        val Default = ScreenBehavior()
    }

}

class ScreenBehaviorBuilder {

    var bottomNavigationState: NavigationBarState = ScreenBehavior.Default.bottomNavigationState
    var navigationRailState: NavigationBarState = ScreenBehavior.Default.navigationRailState
    var onNavigationItemReselected: OnNavigationItemReselected? =
        ScreenBehavior.Default.onNavigationItemReselected

    fun build(): ScreenBehavior = ScreenBehavior(
        bottomNavigationState = bottomNavigationState,
        navigationRailState = navigationRailState,
        onNavigationItemReselected = onNavigationItemReselected,
    )

}

inline fun buildScreenBehavior(builderAction: ScreenBehaviorBuilder.() -> Unit): ScreenBehavior {
    val builder = ScreenBehaviorBuilder()
    builder.builderAction()
    return builder.build()
}
