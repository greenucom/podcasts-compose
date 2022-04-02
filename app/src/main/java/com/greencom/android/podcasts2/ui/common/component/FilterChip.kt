package com.greencom.android.podcasts2.ui.common.component

import android.content.res.Configuration
import androidx.compose.animation.*
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Done
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.greencom.android.podcasts2.ui.theme.PodcastsComposeTheme

private val DefaultIconSize = 24.dp
private val TextStartPaddingSelected = 4.dp
private val TextStartPaddingUnselected = 8.dp

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun FilterChip(
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
    val textStartPadding by animateDpAsState(
        targetValue = if (isSelected) TextStartPaddingSelected else TextStartPaddingUnselected,
        animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
    )

    OutlinedButtonCustom(
        modifier = modifier,
        isChecked = isSelected,
        onClick = { onSelectedChanged(!isSelected) },
    ) {

        AnimatedContent(
            targetState = isSelected,
            transitionSpec = { scaleIn() + fadeIn() with scaleOut() + fadeOut() },
        ) { isSelected ->
            if (isSelected) {
                Icon(imageVector = Icons.Outlined.Done, contentDescription = null)
            }
        }

        // Add Spacer with default icon height to not depend on the presence of the icon
        Spacer(Modifier.height(DefaultIconSize))

        Text(
            modifier = Modifier.padding(start = textStartPadding),
            text = text,
            style = MaterialTheme.typography.body2,
            color = textColor,
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun UnselectedLight() {
    PodcastsComposeTheme {
        var isSelected by remember { mutableStateOf(false) }
        Surface {
            FilterChip(
                modifier = Modifier.padding(16.dp),
                text = "Filter",
                isSelected = isSelected,
                onSelectedChanged = { isSelected = it },
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun SelectedLight() {
    PodcastsComposeTheme {
        Surface {
            FilterChip(
                modifier = Modifier.padding(16.dp),
                text = "Filter",
                isSelected = true,
                onSelectedChanged = {},
            )
        }
    }
}

@Composable
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    locale = "ru",
)
private fun UnselectedDark() {
    PodcastsComposeTheme {
        Surface {
            FilterChip(
                modifier = Modifier.padding(16.dp),
                text = "Filter",
                isSelected = false,
                onSelectedChanged = {},
            )
        }
    }
}

@Composable
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    locale = "ru",
)
private fun SelectedDark() {
    PodcastsComposeTheme {
        Surface {
            FilterChip(
                modifier = Modifier.padding(16.dp),
                text = "Filter",
                isSelected = true,
                onSelectedChanged = {},
            )
        }
    }
}
