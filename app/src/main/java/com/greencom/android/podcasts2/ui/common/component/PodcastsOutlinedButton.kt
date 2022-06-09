package com.greencom.android.podcasts2.ui.common.component

import android.content.res.Configuration
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.greencom.android.podcasts2.ui.theme.PodcastsTheme
import com.greencom.android.podcasts2.ui.theme.onSurfaceUtil

private val MinHeight = 40.dp

private const val BorderColorAlphaChecked = 0.16f
private const val BackgroundColorAlphaChecked = 0.08f

@Composable
fun PodcastsOutlinedButton(
    isChecked: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    content: @Composable RowScope.() -> Unit,
) {

    val borderColorTargetValue = if (isChecked) {
        MaterialTheme.colors.primary.copy(alpha = BorderColorAlphaChecked)
    } else {
        MaterialTheme.colors.onSurfaceUtil
    }
    val borderColor by animateColorAsState(
        targetValue = borderColorTargetValue,
        animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
    )
    val border = BorderStroke(width = 1.dp, color = borderColor)

    val backgroundColor = if (isChecked) {
        MaterialTheme.colors.primary.copy(alpha = BackgroundColorAlphaChecked)
    } else {
        Color.Transparent
    }
    val colors = ButtonDefaults.outlinedButtonColors(backgroundColor = backgroundColor)

    OutlinedButton(
        modifier = modifier.heightIn(min = MinHeight),
        onClick = onClick,
        border = border,
        colors = colors,
        contentPadding = contentPadding,
        content = content,
    )
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    PodcastsTheme {
        Surface {
            var isChecked by remember { mutableStateOf(false) }
            PodcastsOutlinedButton(
                modifier = Modifier.padding(16.dp),
                isChecked = isChecked,
                onClick = { isChecked = !isChecked },
            ) {
                Text(text = "Outlined button")
            }
        }
    }
}
