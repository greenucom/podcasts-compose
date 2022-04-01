package com.greencom.android.podcasts2.ui.common

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

fun PaddingValues.copy(
    layoutDirection: LayoutDirection,
    start: Dp = this.calculateStartPadding(layoutDirection),
    top: Dp = this.calculateTopPadding(),
    end: Dp = this.calculateEndPadding(layoutDirection),
    bottom: Dp = this.calculateBottomPadding(),
): PaddingValues = PaddingValues(
    start = start,
    top = top,
    end = end,
    bottom = bottom,
)

@Composable
fun PaddingValues.copy(
    start: Dp = this.calculateStartPadding(LocalLayoutDirection.current),
    top: Dp = this.calculateTopPadding(),
    end: Dp = this.calculateEndPadding(LocalLayoutDirection.current),
    bottom: Dp = this.calculateBottomPadding(),
): PaddingValues = this.copy(
    layoutDirection = LocalLayoutDirection.current,
    start = start,
    top = top,
    end = end,
    bottom = bottom,
)

@Composable
operator fun PaddingValues.plus(paddingValues: PaddingValues): PaddingValues {
    val ld = LocalLayoutDirection.current
    val start = this.calculateStartPadding(ld) + paddingValues.calculateStartPadding(ld)
    val top = this.calculateTopPadding() + paddingValues.calculateTopPadding()
    val end = this.calculateEndPadding(ld) + paddingValues.calculateEndPadding(ld)
    val bottom = this.calculateBottomPadding() + paddingValues.calculateBottomPadding()
    return PaddingValues(start, top, end, bottom)
}

fun PaddingValues.add(
    layoutDirection: LayoutDirection,
    start: Dp = 0.dp,
    top: Dp = 0.dp,
    end: Dp = 0.dp,
    bottom: Dp = 0.dp,
): PaddingValues = PaddingValues(
    start = this.calculateStartPadding(layoutDirection) + start,
    top = this.calculateTopPadding() + top,
    end = this.calculateEndPadding(layoutDirection) + end,
    bottom = this.calculateBottomPadding() + bottom,
)

@Composable
fun PaddingValues.add(
    start: Dp = 0.dp,
    top: Dp = 0.dp,
    end: Dp = 0.dp,
    bottom: Dp = 0.dp,
): PaddingValues = this.add(
    layoutDirection = LocalLayoutDirection.current,
    start = start,
    top = top,
    end = end,
    bottom = bottom,
)
