package com.greencom.android.podcasts2.ui.common.screenbehavior

data class ScreenBehavior(
    val navigationBarState: NavigationBarState = NavigationBarState.Visible(),
    val navigationRailState: NavigationBarState = NavigationBarState.Visible(),
    val onNavigationItemReselected: OnNavigationItemReselected? = null,
) {

    companion object {
        val Default = ScreenBehavior()
    }

}

class ScreenBehaviorBuilder {

    var navigationBarState: NavigationBarState = ScreenBehavior.Default.navigationBarState
    var navigationRailState: NavigationBarState = ScreenBehavior.Default.navigationRailState
    var onNavigationItemReselected: OnNavigationItemReselected? =
        ScreenBehavior.Default.onNavigationItemReselected

    fun build(): ScreenBehavior = ScreenBehavior(
        navigationBarState = navigationBarState,
        navigationRailState = navigationRailState,
        onNavigationItemReselected = onNavigationItemReselected,
    )

}

fun buildScreenBehavior(builderAction: ScreenBehaviorBuilder.() -> Unit): ScreenBehavior {
    val builder = ScreenBehaviorBuilder()
    builder.builderAction()
    return builder.build()
}
