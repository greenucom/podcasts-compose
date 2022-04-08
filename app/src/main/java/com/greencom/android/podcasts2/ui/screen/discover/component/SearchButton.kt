package com.greencom.android.podcasts2.ui.screen.discover.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.greencom.android.podcasts2.R
import com.greencom.android.podcasts2.ui.common.component.ComponentDefaults
import com.greencom.android.podcasts2.ui.theme.PodcastsComposeTheme

private val Elevation = 2.dp
private const val IconAlpha = 0.6f
private const val TextAlpha = 0.74f

private val BackgroundLight = Color(0xFFF0F0F0)
private val BackgroundDark = Color(0xFF373737)
private val Colors.searchButtonBackground: Color
    get() = if (isLight) BackgroundLight else BackgroundDark

@Composable
fun SearchButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = ButtonDefaults.buttonColors(
        backgroundColor = MaterialTheme.colors.searchButtonBackground,
    )
    val elevation = ButtonDefaults.elevation(
        defaultElevation = Elevation,
        pressedElevation = Elevation,
        disabledElevation = Elevation,
        hoveredElevation = Elevation,
        focusedElevation = Elevation,
    )

    Button(
        modifier = modifier.heightIn(min = ComponentDefaults.MinHeight),
        onClick = onClick,
        shape = MaterialTheme.shapes.small,
        colors = colors,
        elevation = elevation,
    ) {

        Icon(
            modifier = Modifier.alpha(IconAlpha),
            imageVector = Icons.Outlined.Search,
            contentDescription = null,
        )

        Text(
            modifier = Modifier
                .padding(start = 8.dp)
                .weight(1f)
                .alpha(TextAlpha),
            text = stringResource(R.string.search_for_podcasts),
            style = MaterialTheme.typography.body1,
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun Light() {
    PodcastsComposeTheme {
        Surface {
            SearchButton(
                modifier = Modifier.padding(16.dp),
                onClick = {},
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
            SearchButton(
                modifier = Modifier.padding(16.dp),
                onClick = {},
            )
        }
    }
}
