package com.greencom.android.podcasts2.ui.common.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.greencom.android.podcasts2.ui.theme.onSurfaceUtil

object ChipUtils {

    private const val SelectedBackgroundColorAlpha = 0.08f
    private const val SelectedBorderColorAlpha = 0.16f

    @Composable
    fun colors(selected: Boolean): ButtonColors {
        return if (selected) {
            ButtonDefaults.outlinedButtonColors(
                backgroundColor = MaterialTheme.colors.primary.copy(
                    alpha = SelectedBackgroundColorAlpha
                ),
            )
        } else {
            ButtonDefaults.outlinedButtonColors()
        }
    }

    @Composable
    fun border(selected: Boolean): BorderStroke {
        return if (selected) {
            BorderStroke(
                width = 1.dp,
                color = MaterialTheme.colors.primary.copy(alpha = SelectedBorderColorAlpha),
            )
        } else {
            BorderStroke(1.dp, MaterialTheme.colors.onSurfaceUtil)
        }
    }

    @Composable
    fun textColor(selected: Boolean): Color {
        return if (selected) {
            MaterialTheme.colors.primary
        } else {
            MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.medium)
        }
    }

}