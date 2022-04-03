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

fun PaddingValues.add(
    layoutDirection: LayoutDirection,
    paddingValues: PaddingValues,
): PaddingValues = this.add(
    layoutDirection = layoutDirection,
    start = paddingValues.calculateStartPadding(layoutDirection),
    top = paddingValues.calculateTopPadding(),
    end = paddingValues.calculateEndPadding(layoutDirection),
    bottom = paddingValues.calculateBottomPadding(),
)

@Composable
fun PaddingValues.add(
    paddingValues: PaddingValues,
): PaddingValues = this.add(
    layoutDirection = LocalLayoutDirection.current,
    paddingValues = paddingValues,
)

@Composable
operator fun PaddingValues.plus(
    paddingValues: PaddingValues,
): PaddingValues = this.add(
    paddingValues = paddingValues,
)
