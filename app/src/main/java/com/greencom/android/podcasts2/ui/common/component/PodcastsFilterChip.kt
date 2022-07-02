package com.greencom.android.podcasts2.ui.common.component

import android.content.res.Configuration
import androidx.compose.animation.*
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.greencom.android.podcasts2.ui.common.PodcastsIcons
import com.greencom.android.podcasts2.ui.theme.PodcastsTheme

private val DefaultIconSize = 24.dp

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun PodcastsFilterChip(
    text: String,
    isSelected: Boolean,
    onSelectedChanged: (isSelected: Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    val textColorTargetValue = if (isSelected) {
        MaterialTheme.colors.primary
    } else {
        MaterialTheme.colors.onSurface
    }
    val textColor by animateColorAsState(
        targetValue = textColorTargetValue,
        animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
    )

    val paddingStart by animateDpAsState(targetValue = if (isSelected) 8.dp else 16.dp)
    val contentPadding = PaddingValues(
        start = paddingStart,
        top = 8.dp,
        end = 16.dp,
        bottom = 8.dp,
    )

    PodcastsOutlinedButton(
        modifier = modifier,
        isChecked = isSelected,
        onClick = { onSelectedChanged(!isSelected) },
        contentPadding = contentPadding,
    ) {

        AnimatedContent(
            targetState = isSelected,
            transitionSpec = { scaleIn() + fadeIn() with scaleOut() + fadeOut() },
        ) { isSelected ->
            if (isSelected) {
                Icon(
                    modifier = Modifier.padding(end = 4.dp),
                    imageVector = PodcastsIcons.Done,
                    contentDescription = null,
                )
            }
        }

        // Add Spacer with default icon height to not depend on the presence of the icon
        Spacer(modifier = Modifier.height(DefaultIconSize))

        Text(
            text = text,
            style = MaterialTheme.typography.body2,
            color = textColor,
        )
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    PodcastsTheme {
        Surface {
            var isSelected by remember { mutableStateOf(false) }
            PodcastsFilterChip(
                modifier = Modifier.padding(16.dp),
                text = "Filter chip",
                isSelected = isSelected,
                onSelectedChanged = { isSelected = it },
            )
        }
    }
}
