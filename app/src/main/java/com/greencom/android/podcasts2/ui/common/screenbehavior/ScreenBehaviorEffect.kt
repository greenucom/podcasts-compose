package com.greencom.android.podcasts2.ui.common.screenbehavior

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect

@Composable
fun SpecificScreenBehavior(builderAction: (ScreenBehaviorBuilder.() -> Unit)? = null) {
    val screenBehaviorController = LocalScreenBehaviorController.current
    DisposableEffect(Unit) {
        val screenBehavior = if (builderAction != null) {
            buildScreenBehavior(builderAction)
        } else {
            ScreenBehavior.Default
        }
        screenBehaviorController.set(screenBehavior)

        onDispose { screenBehaviorController.remove(screenBehavior) }
    }
}
