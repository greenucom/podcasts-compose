package com.greencom.android.podcasts2.ui.common.screenbehavior

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect

@Composable
fun SpecificScreenBehavior(builderAction: (ScreenBehaviorBuilder.() -> Unit)) {
    val screenBehaviorController = LocalScreenBehaviorController.current ?: return
    DisposableEffect(Unit) {
        val screenBehavior = buildScreenBehavior(builderAction)
        screenBehaviorController.setScreenBehavior(screenBehavior)

        onDispose { screenBehaviorController.removeScreenBehavior(screenBehavior) }
    }
}

@Composable
fun DefaultScreenBehavior() {
    val screenBehaviorController = LocalScreenBehaviorController.current ?: return
    DisposableEffect(Unit) {
        val defaultScreenBehavior = ScreenBehavior.Default
        screenBehaviorController.setScreenBehavior(defaultScreenBehavior)

        onDispose { screenBehaviorController.removeScreenBehavior(defaultScreenBehavior) }
    }
}
