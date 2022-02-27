package com.greencom.android.podcasts2.ui.common.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.greencom.android.podcasts2.ui.theme.onSurfaceUtil

object FilterChipUtils {

    val ContentMinHeightDp = 24.dp

    private val ContentPaddingVerticalDp = 8.dp
    private val ContentPaddingEndDp = 14.dp
    private val ContentPaddingStartUnselectedDp = ContentPaddingEndDp
    private val ContentPaddingStartSelectedDp = 8.dp

    private const val SelectedBackgroundColorAlpha =
        OutlinedButtonUtils.CheckedBackgroundColorAlpha
    private const val SelectedBorderColorAlpha =
        OutlinedButtonUtils.CheckedBorderColorAlpha

    val FilterChipRippleTheme = object : RippleTheme {

        @Composable
        override fun defaultColor(): Color = MaterialTheme.colors.primary

        @Composable
        override fun rippleAlpha(): RippleAlpha = RippleTheme.defaultRippleAlpha(
            contentColor = MaterialTheme.colors.primary,
            lightTheme = MaterialTheme.colors.isLight,
        )

    }

    @Composable
    fun backgroundColor(isSelected: Boolean): Color {
        return if (isSelected) {
            MaterialTheme.colors.primary.copy(
                alpha = SelectedBackgroundColorAlpha
            )
        } else {
            MaterialTheme.colors.surface
        }
    }

    @Composable
    fun borderColor(isSelected: Boolean): Color {
        return if (isSelected) {
            MaterialTheme.colors.primary.copy(
                alpha = SelectedBorderColorAlpha
            )
        } else {
            MaterialTheme.colors.onSurfaceUtil
        }
    }

    @Composable
    fun textColor(isSelected: Boolean): Color {
        return if (isSelected) {
            MaterialTheme.colors.primary
        } else {
            MaterialTheme.colors.onSurface
        }
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
