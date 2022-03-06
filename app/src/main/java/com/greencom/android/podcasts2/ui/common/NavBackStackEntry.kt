package com.greencom.android.podcasts2.ui.common

import androidx.navigation.NavBackStackEntry

fun NavBackStackEntry.requireLong(key: String): Long {
    return requireNotNull(this.arguments?.getLong(key))
}
