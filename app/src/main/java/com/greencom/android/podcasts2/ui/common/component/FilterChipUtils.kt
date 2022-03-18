package com.greencom.android.podcasts2.ui.common.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

object FilterChipUtils {

    val MinHeightDp = 40.dp

    private val ContentPaddingVerticalDp = 8.dp
    private val ContentPaddingEndDp = 14.dp
    private val ContentPaddingStartUnselectedDp = ContentPaddingEndDp
    private val ContentPaddingStartSelectedDp = 8.dp

    val FilterChipRippleTheme = object : RippleTheme {

        @Composable
        override fun defaultColor(): Color = MaterialTheme.colors.primary

        @Composable
        override fun rippleAlpha(): RippleAlpha = RippleTheme.defaultRippleAlpha(
            contentColor = MaterialTheme.colors.primary,
            lightTheme = MaterialTheme.colors.isLight,
        )

    }

    fun contentPaddingStart(isSelected: Boolean): Dp {
        return if (isSelected) {
            ContentPaddingStartSelectedDp
        } else {
            ContentPaddingStartUnselectedDp
        }
    }

    fun contentPadding(start: Dp): PaddingValues {
        return PaddingValues(
            start = start,
            top = ContentPaddingVerticalDp,
            end = ContentPaddingEndDp,
            bottom = ContentPaddingVerticalDp,
        )
    }

}
