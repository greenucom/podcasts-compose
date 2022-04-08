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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.greencom.android.podcasts2.R
import com.greencom.android.podcasts2.ui.common.component.ComponentDefaults
import com.greencom.android.podcasts2.ui.theme.PodcastsComposeTheme
import com.greencom.android.podcasts2.ui.theme.searchBackground

@Composable
fun SearchButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = ButtonDefaults.buttonColors(
        backgroundColor = MaterialTheme.colors.searchBackground,
    )
    val elevation = ButtonDefaults.elevation(
        defaultElevation = 0.dp,
        pressedElevation = 0.dp,
        disabledElevation = 0.dp,
        hoveredElevation = 0.dp,
        focusedElevation = 0.dp,
    )

    Button(
        modifier = modifier.heightIn(min = ComponentDefaults.MinHeight),
        onClick = onClick,
        shape = MaterialTheme.shapes.small,
        colors = colors,
        elevation = elevation,
    ) {

        Icon(
            modifier = Modifier.alpha(ComponentDefaults.IconAlpha),
            imageVector = Icons.Outlined.Search,
            contentDescription = null,
        )

        Text(
            modifier = Modifier
                .padding(start = 8.dp)
                .weight(1f)
                .alpha(ComponentDefaults.TextAlpha),
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
