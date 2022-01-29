package com.greencom.podcasts2

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.greencom.podcasts2.ui.theme.PodcastsComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PodcastsComposeTheme {

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Light() {
    PodcastsComposeTheme {

    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Dark() {
    PodcastsComposeTheme {

    }
}
