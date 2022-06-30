package com.greencom.android.podcasts2.ui.common

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.graphics.Color

private const val AnimationDurationInMillis = 1000

private val StartColorLight = Color(0xFFE5E5E5)
private val StartColorDark = Color(0xFF383838)
private val EndColorLight = Color(0xFFD9D9D9)
private val EndColorDark = Color(0xFF444444)
val Colors.placeholderLoadingStart: Color
    get() = if (isLight) StartColorLight else StartColorDark
val Colors.placeholderLoadingEnd: Color
    get() = if (isLight) EndColorLight else EndColorDark

@Composable
fun animatePlaceholderLoadingColor(
    startColor: Color = MaterialTheme.colors.placeholderLoadingStart,
    endColor: Color = MaterialTheme.colors.placeholderLoadingEnd,
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
