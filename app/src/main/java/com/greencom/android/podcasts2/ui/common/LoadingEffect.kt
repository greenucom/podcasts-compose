package com.greencom.android.podcasts2.ui.common

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import com.greencom.android.podcasts2.ui.theme.loadingEffectEnd
import com.greencom.android.podcasts2.ui.theme.loadingEffectStart

@Composable
fun LoadingEffect(
    onLoadingEffectChanged: @Composable (color: Color) -> Unit,
) {
    val transition = rememberInfiniteTransition()

    val color by transition.animateColor(
        initialValue = MaterialTheme.colors.loadingEffectStart,
        targetValue = MaterialTheme.colors.loadingEffectEnd,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1500,
                easing = LinearEasing,
            ),
            repeatMode = RepeatMode.Reverse,
        )
    )

    onLoadingEffectChanged(color)
}
