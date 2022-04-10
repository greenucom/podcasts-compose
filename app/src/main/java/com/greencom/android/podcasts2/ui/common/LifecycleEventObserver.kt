package com.greencom.android.podcasts2.ui.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

@Composable
fun LifecycleEventObserver(onEvent: (owner: LifecycleOwner, event: Lifecycle.Event) -> Unit) {
    val eventHandler by rememberUpdatedState(onEvent)
    val lifecycle by rememberUpdatedState(LocalLifecycleOwner.current.lifecycle)

    DisposableEffect(lifecycle) {
        val observer = LifecycleEventObserver { owner, event ->
            eventHandler(owner, event)
        }
        lifecycle.addObserver(observer)

        onDispose { lifecycle.removeObserver(observer) }
    }
}
