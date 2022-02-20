package com.greencom.android.podcasts2.ui.screen.library

import android.content.res.Configuration
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.greencom.android.podcasts2.R
import com.greencom.android.podcasts2.ui.theme.PodcastsComposeTheme

@Composable
fun LibraryScreen(modifier: Modifier = Modifier) {
    Text(stringResource(R.string.library))
}

@Composable
@Preview(showBackground = true)
private fun Light() {
    PodcastsComposeTheme {
        LibraryScreen()
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
        LibraryScreen()
    }
}
