package com.greencom.android.podcasts2.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.launch

fun MutableStateFlow<Job?>.cancel() {
    this.getAndUpdate { null }?.cancel()
}

inline fun MutableStateFlow<Job?>.cancelAndLaunchIn(
    scope: CoroutineScope,
    crossinline block: suspend CoroutineScope.() -> Unit,
) {
    this.getAndUpdate {
        scope.launch { block() }
    }?.cancel()
}
