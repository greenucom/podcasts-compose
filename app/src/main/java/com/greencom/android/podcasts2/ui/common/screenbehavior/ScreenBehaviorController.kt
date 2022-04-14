package com.greencom.android.podcasts2.ui.common.screenbehavior

import androidx.compose.runtime.staticCompositionLocalOf

interface ScreenBehaviorController {
    fun set(screenBehavior: ScreenBehavior)
    fun remove(screenBehavior: ScreenBehavior)
}

val LocalScreenBehaviorController =
    staticCompositionLocalOf<ScreenBehaviorController> {
        object : ScreenBehaviorController {
            override fun set(screenBehavior: ScreenBehavior) {}
            override fun remove(screenBehavior: ScreenBehavior) {}
        }
    }
