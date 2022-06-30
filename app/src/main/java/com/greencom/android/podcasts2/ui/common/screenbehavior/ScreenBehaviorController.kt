package com.greencom.android.podcasts2.ui.common.screenbehavior

import androidx.compose.runtime.staticCompositionLocalOf

interface ScreenBehaviorController {
    val currentScreenBehavior: ScreenBehavior
    fun setScreenBehavior(screenBehavior: ScreenBehavior)
    fun removeScreenBehavior(screenBehavior: ScreenBehavior)
}

val LocalScreenBehaviorController =
    staticCompositionLocalOf<ScreenBehaviorController?> { null }
