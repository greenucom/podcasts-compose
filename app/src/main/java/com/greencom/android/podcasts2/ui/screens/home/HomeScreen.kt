package com.greencom.android.podcasts2.ui.screens.home

import android.content.res.Configuration
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.greencom.android.podcasts2.R
import com.greencom.android.podcasts2.ui.theme.PodcastsComposeTheme

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Text(stringResource(R.string.home))
}

@Preview(showBackground = true)
@Composable
private fun Light() {
    PodcastsComposeTheme {
        HomeScreen()
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, locale = "ru")
@Composable
private fun Dark() {
    PodcastsComposeTheme {
        HomeScreen()
    }
}
