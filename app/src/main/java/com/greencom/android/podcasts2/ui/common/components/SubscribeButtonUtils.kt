package com.greencom.android.podcasts2.ui.common.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.greencom.android.podcasts2.ui.theme.onSurfaceUtil

object SubscribeButtonUtils {

    private const val SubscribedBackgroundColorAlpha = 0.08f
    private const val SubscribedBorderColorAlpha = 0.16f

    @Composable
    fun colors(isSubscribed: Boolean): ButtonColors {
        return if (isSubscribed) {
            ButtonDefaults.outlinedButtonColors(
                backgroundColor = MaterialTheme.colors.primary.copy(
                    alpha = SubscribedBackgroundColorAlpha
                ),
            )
        } else {
            ButtonDefaults.outlinedButtonColors()
        }
    }

    @Composable
    fun border(isSubscribed: Boolean): BorderStroke {
        return if (isSubscribed) {
            BorderStroke(
                width = 1.dp,
                color = MaterialTheme.colors.primary.copy(alpha = SubscribedBorderColorAlpha),
            )
        } else {
            BorderStroke(1.dp, MaterialTheme.colors.onSurfaceUtil)
        }
    }

}
