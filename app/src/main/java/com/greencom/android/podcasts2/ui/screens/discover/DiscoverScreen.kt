package com.greencom.android.podcasts2.ui.screens.discover

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.greencom.android.podcasts2.ui.screens.discover.components.SearchBar
import com.greencom.android.podcasts2.ui.screens.discover.components.SearchBarUtils
import com.greencom.android.podcasts2.ui.theme.PodcastsComposeTheme

@Composable
fun DiscoverScreen(
    modifier: Modifier = Modifier,
    discoverViewModel: DiscoverViewModel = hiltViewModel(),
) {
    Surface(modifier = modifier.fillMaxSize()) {
        Column {
            SearchBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(SearchBarUtils.Height)
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                value = "TODO",
                onValueChange = { /* TODO */ },
                onImeSearch = { /* TODO */ },
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun Light() {
    PodcastsComposeTheme {
        DiscoverScreen()
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
        DiscoverScreen()
    }
}
