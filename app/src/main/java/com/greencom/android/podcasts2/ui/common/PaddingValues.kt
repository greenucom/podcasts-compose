package com.greencom.android.podcasts2.ui.common

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

fun PaddingValues.plus(
    start: Dp = 0.dp,
    top: Dp = 0.dp,
    end: Dp = 0.dp,
    bottom: Dp = 0.dp,
    layoutDirection: LayoutDirection,
): PaddingValues {
    val paddingValues = PaddingValues(start, top, end, bottom)
    return this.plus(paddingValues, layoutDirection)
}

fun PaddingValues.plus(
    paddingValues: PaddingValues,
    layoutDirection: LayoutDirection,
): PaddingValues {
    val start = this.calculateStartPadding(layoutDirection) +
            paddingValues.calculateStartPadding(layoutDirection)
    val top = this.calculateTopPadding() + paddingValues.calculateTopPadding()
    val end = this.calculateEndPadding(layoutDirection) +
            paddingValues.calculateEndPadding(layoutDirection)
    val bottom = this.calculateBottomPadding() + paddingValues.calculateBottomPadding()
    return PaddingValues(start, top, end, bottom)
}

fun PaddingValues.copy(
    layoutDirection: LayoutDirection,
    start: Dp = this.calculateStartPadding(layoutDirection),
    top: Dp = this.calculateTopPadding(),
    end: Dp = this.calculateEndPadding(layoutDirection),
    bottom: Dp = this.calculateBottomPadding(),
): PaddingValues = PaddingValues(start, top, end, bottom)
