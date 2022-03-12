package com.greencom.android.podcasts2.ui.screen.mypodcasts

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.greencom.android.podcasts2.ui.theme.PodcastsComposeTheme

@Composable
fun MyPodcastsScreen(modifier: Modifier = Modifier) {

}

@Composable
@Preview(showBackground = true)
private fun Light() {
    PodcastsComposeTheme {
        MyPodcastsScreen()
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
        MyPodcastsScreen()
    }
}
