package com.greencom.android.podcasts2.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.launch

inline fun MutableStateFlow<Job?>.relaunchIn(
    scope: CoroutineScope,
    crossinline block: suspend CoroutineScope.() -> Unit,
) {
    this.getAndUpdate {
        scope.launch { block() }
    }?.cancel()
}

fun MutableStateFlow<Job?>.cancel() {
    this.getAndUpdate { null }?.cancel()
}

suspend fun MutableStateFlow<Job?>.join() {
    this.getAndUpdate { null }?.join()
}
