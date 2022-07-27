package com.greencom.android.podcasts2.ui.common.screenbehavior

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import timber.log.Timber

const val TagScreenBehavior = "ScreenBehavior"

@Composable
fun SpecificScreenBehavior(
    key1: Any?,
    builderAction: (ScreenBehaviorBuilder.() -> Unit),
) {
    SpecificScreenBehaviorImpl(key1, builderAction = builderAction)
}

@Composable
fun SpecificScreenBehavior(
    key1: Any?,
    key2: Any?,
    builderAction: (ScreenBehaviorBuilder.() -> Unit),
) {
    SpecificScreenBehaviorImpl(key1, key2, builderAction = builderAction)
}

@Composable
fun SpecificScreenBehavior(
    key1: Any?,
    key2: Any?,
    key3: Any?,
    builderAction: (ScreenBehaviorBuilder.() -> Unit),
) {
    SpecificScreenBehaviorImpl(key1, key2, key3, builderAction = builderAction)
}

@Composable
fun SpecificScreenBehavior(
    vararg keys: Any?,
    builderAction: (ScreenBehaviorBuilder.() -> Unit),
) {
    SpecificScreenBehaviorImpl(*keys, builderAction = builderAction)
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

@Composable
private inline fun SpecificScreenBehaviorImpl(
    vararg keys: Any?,
    crossinline builderAction: (ScreenBehaviorBuilder.() -> Unit),
) {
    val screenBehaviorController = LocalScreenBehaviorController.current ?: return
    DisposableEffect(*keys) {
        val screenBehaviorBuilder = ScreenBehaviorBuilder()
        screenBehaviorBuilder.builderAction()
        val screenBehavior = screenBehaviorBuilder.build()
        screenBehaviorController.setScreenBehavior(screenBehavior)
        Timber.tag(TagScreenBehavior).d("$screenBehavior set")

        onDispose {
            screenBehaviorController.removeScreenBehavior(screenBehavior)
            Timber.tag(TagScreenBehavior).d("$screenBehavior removed")
        }
    }
}
