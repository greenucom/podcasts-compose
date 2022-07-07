package com.greencom.android.podcasts2.ui.common.screenbehavior

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect

@Composable
inline fun SpecificScreenBehavior(crossinline builderAction: (ScreenBehaviorBuilder.() -> Unit)) {
    val screenBehaviorController = LocalScreenBehaviorController.current ?: return
    DisposableEffect(Unit) {
        val screenBehaviorBuilder = ScreenBehaviorBuilder()
        screenBehaviorBuilder.builderAction()
        val screenBehavior = screenBehaviorBuilder.build()
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
