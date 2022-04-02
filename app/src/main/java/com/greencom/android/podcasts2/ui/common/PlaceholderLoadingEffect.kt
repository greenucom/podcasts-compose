package com.greencom.android.podcasts2.ui.common

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.graphics.Color

private const val AnimationDurationInMillis = 1000

private val StartLight = Color(0xFFE5E5E5)
private val StartDark = Color(0xFF383838)
private val EndLight = Color(0xFFD9D9D9)
private val EndDark = Color(0xFF444444)
private val Colors.placeholderLoadingEffectStart: Color
    get() = if (isLight) StartLight else StartDark
private val Colors.placeholderLoadingEffectEnd: Color
    get() = if (isLight) EndLight else EndDark

@Composable
fun animatePlaceholderLoadingEffectColor(
    startColor: Color = MaterialTheme.colors.placeholderLoadingEffectStart,
    endColor: Color = MaterialTheme.colors.placeholderLoadingEffectEnd,
): State<Color> {
    val transition = rememberInfiniteTransition()
    return transition.animateColor(
        initialValue = startColor,
        targetValue = endColor,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = AnimationDurationInMillis,
                easing = LinearEasing,
            ),
            repeatMode = RepeatMode.Reverse,
        ),
    )
}
