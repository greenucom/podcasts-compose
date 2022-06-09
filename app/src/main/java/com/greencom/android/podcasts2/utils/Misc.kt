package com.greencom.android.podcasts2.utils

fun Int.toBoolean(): Boolean = when (this) {
    0 -> false
    1 -> true
    else -> throw IllegalArgumentException("Value should be either 0 or 1")
}
