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
import com.greencom.android.podcasts2.ui.theme.PodcastsComposeTheme

@Composable
fun SearchButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier.heightIn(SearchButtonUtils.MinHeightDp),
        onClick = onClick,
        shape = MaterialTheme.shapes.small,
        colors = SearchButtonUtils.colors(),
        elevation = SearchButtonUtils.elevation(),
    ) {
        Icon(
            modifier = Modifier.alpha(SearchButtonUtils.IconAlpha),
            imageVector = Icons.Outlined.Search,
            contentDescription = null,
        )

        Text(
            modifier = Modifier
                .padding(start = 8.dp)
                .weight(1f)
                .alpha(SearchButtonUtils.TextAlpha),
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
