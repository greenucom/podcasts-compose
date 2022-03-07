package com.greencom.android.podcasts2.ui.screen.search.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.statusBarsHeight
import com.greencom.android.podcasts2.ui.theme.PodcastsComposeTheme

@Composable
fun SearchTopBar(
    searchValue: String,
    onSearchValueChanged: (value: String) -> Unit,
    onSearch: (value: String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Spacer(Modifier.statusBarsHeight())

        TopAppBar(
            backgroundColor = Color.Transparent,
            elevation = 0.dp,
            contentPadding = PaddingValues(0.dp),
        ) {
            SearchTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                value = searchValue,
                onValueChanged = onSearchValueChanged,
                onSearch = onSearch,
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun Light() {
    PodcastsComposeTheme {
        Surface {
            SearchTopBar(
                searchValue = "The Big Beard Theory",
                onSearchValueChanged = {},
                onSearch = {},
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
            SearchTopBar(
                searchValue = "",
                onSearchValueChanged = {},
                onSearch = {},
            )
        }
    }
}
