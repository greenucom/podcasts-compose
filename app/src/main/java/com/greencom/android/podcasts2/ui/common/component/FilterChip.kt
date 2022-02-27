package com.greencom.android.podcasts2.ui.common.component

import android.content.res.Configuration
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Done
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.greencom.android.podcasts2.ui.theme.PodcastsComposeTheme

@OptIn(ExperimentalMaterialApi::class, ExperimentalAnimationApi::class)
@Composable
fun FilterChip(
    isSelected: Boolean,
    onSelectedChanged: (isSelected: Boolean) -> Unit,
    text: String,
    modifier: Modifier = Modifier,
) {
    val backgroundColor = FilterChipUtils.backgroundColor(isSelected)
    val borderColor by animateColorAsState(
        targetValue = FilterChipUtils.borderColor(isSelected),
        animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
    )

    CompositionLocalProvider(LocalRippleTheme provides FilterChipUtils.FilterChipRippleTheme) {
        Surface(
            modifier = modifier,
            onClick = { onSelectedChanged(!isSelected) },
            shape = MaterialTheme.shapes.small,
            color = backgroundColor,
            border = BorderStroke(width = 1.dp, color = borderColor),
        ) {
            val paddingStart by animateDpAsState(
                FilterChipUtils.contentPaddingStart(isSelected)
            )
            val paddingValues = FilterChipUtils.contentPadding(paddingStart)

            val textColor by animateColorAsState(
                targetValue = FilterChipUtils.textColor(isSelected),
                animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
            )

            Row(
                modifier = Modifier
                    .padding(paddingValues)
                    .heightIn(min = FilterChipUtils.ContentMinHeightDp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                AnimatedContent(isSelected) { isSelected ->
                    if (isSelected) {
                        Icon(
                            modifier = Modifier.padding(end = 8.dp),
                            imageVector = Icons.Outlined.Done,
                            contentDescription = null,
                            tint = MaterialTheme.colors.primary,
                        )
                    }
                }

                Text(
                    text = text,
                    style = MaterialTheme.typography.body1,
                    color = textColor,
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun Light() {
    PodcastsComposeTheme {
        Surface {
            FilterChip(
                modifier = Modifier.padding(16.dp),
                isSelected = false,
                onSelectedChanged = {},
                text = "Lorem ipsum",
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun LightSelected() {
    PodcastsComposeTheme {
        Surface {
            FilterChip(
                modifier = Modifier.padding(16.dp),
                isSelected = true,
                onSelectedChanged = {},
                text = "Lorem ipsum",
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
private fun Dark() {
    PodcastsComposeTheme {
        Surface {
            FilterChip(
                modifier = Modifier.padding(16.dp),
                isSelected = false,
                onSelectedChanged = {},
                text = "Lorem ipsum",
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
private fun DarkSelected() {
    PodcastsComposeTheme {
        Surface {
            FilterChip(
                modifier = Modifier.padding(16.dp),
                isSelected = true,
                onSelectedChanged = {},
                text = "Lorem ipsum",
            )
        }
    }
}
