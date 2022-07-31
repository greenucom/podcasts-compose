package com.greencom.android.podcasts2.ui.common.mvi

import kotlin.time.Duration.Companion.milliseconds

interface Event

object DebouncedEventTimeouts {
    val navigationTimeout = 500.milliseconds
}
