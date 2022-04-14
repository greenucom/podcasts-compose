package com.greencom.android.podcasts2.ui.common.screenbehavior

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect

@Composable
fun SpecificScreenBehavior(screenBehaviorBuilder: (() -> ScreenBehavior)? = null) {
    val screenBehaviorController = LocalScreenBehaviorController.current
    DisposableEffect(Unit) {
        val screenBehavior = screenBehaviorBuilder?.invoke() ?: ScreenBehavior.Default
        screenBehaviorController.set(screenBehavior)

        onDispose { screenBehaviorController.remove(screenBehavior) }
    }
}
