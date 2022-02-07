package com.greencom.android.podcasts2.ui.common.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.greencom.android.podcasts2.ui.theme.PodcastsComposeTheme

@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier,
    text: String,
) {
    Surface(modifier = modifier.fillMaxSize()) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            CircularProgressIndicator()

            Spacer(Modifier.height(8.dp))

            Text(
                modifier = Modifier.padding(horizontal = 48.dp),
                text = text,
                style = MaterialTheme.typography.body1,
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun Light() {
    PodcastsComposeTheme {
        LoadingScreen(text = "Loading podcasts...")
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
        LoadingScreen(text = "Загрузка подкастов...")
    }
}
