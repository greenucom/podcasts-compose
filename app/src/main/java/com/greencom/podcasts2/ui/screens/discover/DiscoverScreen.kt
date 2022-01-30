package com.greencom.podcasts2.ui.screens.discover

import android.content.res.Configuration
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.greencom.podcasts2.R
import com.greencom.podcasts2.ui.theme.PodcastsComposeTheme

@Composable
fun DiscoverScreen(modifier: Modifier = Modifier) {
    Text(stringResource(R.string.discover))
}

@Preview(showBackground = true)
@Composable
private fun Light() {
    PodcastsComposeTheme {
        DiscoverScreen()
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, locale = "ru")
@Composable
private fun Dark() {
    PodcastsComposeTheme {
        DiscoverScreen()
    }
}
