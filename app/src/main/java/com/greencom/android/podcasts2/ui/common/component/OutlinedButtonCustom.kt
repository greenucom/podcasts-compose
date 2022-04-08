package com.greencom.android.podcasts2.ui.common.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.greencom.android.podcasts2.ui.theme.onSurfaceUtil

private const val CheckedBorderColorAlpha = 0.16f
private const val CheckedBackgroundColorAlpha = 0.08f

@Composable
fun OutlinedButtonCustom(
    isChecked: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit,
) {

    val borderColorTargetValue = if (isChecked) {
        MaterialTheme.colors.primary.copy(alpha = CheckedBorderColorAlpha)
    } else {
        MaterialTheme.colors.onSurfaceUtil
    }
    val borderColor by animateColorAsState(
        targetValue = borderColorTargetValue,
        animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
    )
    val border = BorderStroke(
        width = 1.dp,
        color = borderColor,
    )

    val backgroundColor = if (isChecked) {
        MaterialTheme.colors.primary.copy(alpha = CheckedBackgroundColorAlpha)
    } else {
        Color.Transparent
    }
    val colors = ButtonDefaults.outlinedButtonColors(
        backgroundColor = backgroundColor,
    )

    val contentPadding = PaddingValues(
        start = 8.dp, top = 8.dp, end = 16.dp, bottom = 8.dp,
    )

    OutlinedButton(
        modifier = modifier.heightIn(min = ComponentDefaults.MinHeight),
        onClick = onClick,
        border = border,
        colors = colors,
        contentPadding = contentPadding,
        content = content,
    )
}
