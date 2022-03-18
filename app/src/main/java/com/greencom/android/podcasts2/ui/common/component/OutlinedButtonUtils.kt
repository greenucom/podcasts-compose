package com.greencom.android.podcasts2.ui.common.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.greencom.android.podcasts2.ui.theme.onSurfaceUtil

object OutlinedButtonUtils {

    const val CheckedBackgroundColorAlpha = 0.08f
    const val CheckedBorderColorAlpha = 0.16f

    @Composable
    fun backgroundColor(isChecked: Boolean): Color {
        return if (isChecked) {
            MaterialTheme.colors.primary.copy(
                alpha = CheckedBackgroundColorAlpha
            )
        } else {
            MaterialTheme.colors.surface
        }
    }

    @Composable
    fun borderColor(isChecked: Boolean): Color {
        return if (isChecked) {
            MaterialTheme.colors.primary.copy(
                alpha = CheckedBorderColorAlpha
            )
        } else {
            MaterialTheme.colors.onSurfaceUtil
        }
    }

    @Composable
    fun contentColor(isChecked: Boolean): Color {
        return if (isChecked) MaterialTheme.colors.primary else MaterialTheme.colors.onSurface
    }

    val contentPadding: PaddingValues
        get() = PaddingValues(start = 8.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)

}
