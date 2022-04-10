package com.greencom.android.podcasts2.ui.common

import androidx.navigation.NavBackStackEntry

fun NavBackStackEntry.requireString(key: String): String {
    return requireNotNull(this.arguments?.getString(key))
}

fun NavBackStackEntry.requireInt(key: String): Int {
    return requireNotNull(this.arguments?.getInt(key))
}

fun NavBackStackEntry.requireLong(key: String): Long {
    return requireNotNull(this.arguments?.getLong(key))
}
