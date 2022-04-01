package com.greencom.android.podcasts2.ui.common

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class ContentBottomPadding(val value: Dp = 0.dp)

val LocalContentBottomPadding = compositionLocalOf {
    ContentBottomPadding()
}
