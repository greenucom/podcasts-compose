package com.greencom.android.podcasts2.ui.common

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.Dp

@Composable
fun PaddingValues.copy(
    start: Dp = this.calculateStartPadding(LocalLayoutDirection.current),
    top: Dp = this.calculateTopPadding(),
    end: Dp = this.calculateEndPadding(LocalLayoutDirection.current),
    bottom: Dp = this.calculateBottomPadding(),
): PaddingValues = PaddingValues(start, top, end, bottom)
