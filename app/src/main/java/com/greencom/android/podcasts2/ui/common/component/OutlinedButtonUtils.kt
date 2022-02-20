package com.greencom.android.podcasts2.ui.common.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.greencom.android.podcasts2.ui.theme.onSurfaceUtil

object OutlinedButtonUtils {

    private const val CheckedBackgroundColorAlpha = 0.08f
    private const val CheckedBorderColorAlpha = 0.16f

    @Composable
    fun colors(isChecked: Boolean): ButtonColors {
        return if (isChecked) {
            ButtonDefaults.outlinedButtonColors(
                backgroundColor = MaterialTheme.colors.primary.copy(
                    alpha = CheckedBackgroundColorAlpha
                ),
            )
        } else {
            ButtonDefaults.outlinedButtonColors()
        }
    }

    @Composable
    fun border(isChecked: Boolean): BorderStroke {
        return if (isChecked) {
            BorderStroke(
                width = 1.dp,
                color = MaterialTheme.colors.primary.copy(alpha = CheckedBorderColorAlpha),
            )
        } else {
            BorderStroke(1.dp, MaterialTheme.colors.onSurfaceUtil)
        }
    }

}
