package com.greencom.android.podcasts2.ui.common.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.greencom.android.podcasts2.ui.theme.PodcastsComposeTheme

@Composable
fun Chip(
    text: String,
    selected: Boolean,
    onSelectedChange: (selected: Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedButton(
        modifier = modifier,
        onClick = { onSelectedChange(!selected) },
        shape = MaterialTheme.shapes.small,
        colors = ChipUtils.colors(selected),
        border = ChipUtils.border(selected),
    ) {
        Text(
            text = text,
            color = ChipUtils.textColor(selected),
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun LightUnselected() {
    PodcastsComposeTheme {
        Surface {
            Chip(
                text = "News",
                selected = false,
                onSelectedChange = {},
                modifier = Modifier.padding(8.dp),
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun LightSelected() {
    PodcastsComposeTheme {
        Surface {
            Chip(
                text = "Новости",
                selected = true,
                onSelectedChange = {},
                modifier = Modifier.padding(8.dp),
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
private fun DarkUnselected() {
    PodcastsComposeTheme {
        Surface {
            Chip(
                text = "News",
                selected = false,
                onSelectedChange = {},
                modifier = Modifier.padding(8.dp),
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
            Chip(
                text = "Новости",
                selected = true,
                onSelectedChange = {},
                modifier = Modifier.padding(8.dp),
            )
        }
    }
}
