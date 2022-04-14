package com.greencom.android.podcasts2.ui.common.screenbehavior

import com.greencom.android.podcasts2.ui.common.BottomNavBarState
import com.greencom.android.podcasts2.ui.navigation.BottomNavBarItem

typealias OnBottomNavBarItemReselected = (BottomNavBarItem) -> Boolean

class ScreenBehavior(
    val bottomNavBarState: BottomNavBarState = BottomNavBarState.Visible,
    val onBottomNavBarItemReselected: OnBottomNavBarItemReselected? = null,
) {

    companion object {
        val Default: ScreenBehavior = ScreenBehavior()
    }

}
