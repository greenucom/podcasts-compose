package com.greencom.android.podcasts2.ui.common

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import com.greencom.android.podcasts2.ui.theme.loadingEffectEnd
import com.greencom.android.podcasts2.ui.theme.loadingEffectStart

private const val Duration = 1000

@Composable
fun rememberPlaceholderLoadingColor(
    startColor: Color = MaterialTheme.colors.loadingEffectStart,
    endColor: Color = MaterialTheme.colors.loadingEffectEnd,
): State<Color> {
    val transition = rememberInfiniteTransition()
    return transition.animateColor(
        initialValue = startColor,
        targetValue = endColor,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = Duration,
                easing = LinearEasing,
            ),
            repeatMode = RepeatMode.Reverse,
        )
    )
}

@Composable
fun PlaceholderLoadingEffect(
    startColor: Color = MaterialTheme.colors.loadingEffectStart,
    endColor: Color = MaterialTheme.colors.loadingEffectEnd,
    onLoadingEffectChanged: @Composable (color: Color) -> Unit,
) {
    val color by rememberPlaceholderLoadingColor(
        startColor = startColor,
        endColor = endColor,
    )
    onLoadingEffectChanged(color)
}
