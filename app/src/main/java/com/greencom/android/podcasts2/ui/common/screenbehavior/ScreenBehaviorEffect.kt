package com.greencom.android.podcasts2.ui.common.screenbehavior

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import timber.log.Timber

const val TagScreenBehavior = "ScreenBehavior"

@Composable
inline fun SpecificScreenBehavior(
    vararg keys: Any? = arrayOf(Unit),
    crossinline builderAction: (ScreenBehaviorBuilder.() -> Unit),
) {
    val screenBehaviorController = LocalScreenBehaviorController.current ?: return
    DisposableEffect(*keys) {
        val screenBehaviorBuilder = ScreenBehaviorBuilder()
        screenBehaviorBuilder.builderAction()
        val screenBehavior = screenBehaviorBuilder.build()
        screenBehaviorController.setScreenBehavior(screenBehavior)
        Timber.tag(TagScreenBehavior).d("SpecificScreenBehavior set")

        onDispose {
            screenBehaviorController.removeScreenBehavior(screenBehavior)
            Timber.tag(TagScreenBehavior).d("SpecificScreenBehavior removed")
        }
    }
}

@Composable
fun DefaultScreenBehavior() {
    val screenBehaviorController = LocalScreenBehaviorController.current ?: return
    DisposableEffect(Unit) {
        val defaultScreenBehavior = ScreenBehavior.Default
        screenBehaviorController.setScreenBehavior(defaultScreenBehavior)
        Timber.tag(TagScreenBehavior).d("DefaultScreenBehavior set")

        onDispose {
            screenBehaviorController.removeScreenBehavior(defaultScreenBehavior)
            Timber.tag(TagScreenBehavior).d("DefaultScreenBehavior removed")
        }
    }
}
