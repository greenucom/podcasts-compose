package com.greencom.android.podcasts2.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.greencom.android.podcasts2.ui.screen.app.AppScreen
import com.greencom.android.podcasts2.ui.theme.PodcastsComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            PodcastsComposeTheme {
                SystemUi()

                ProvideWindowInsets {
                    Surface {
                        AppScreen()
                    }
                }
            }
        }
    }
}

@Composable
fun SystemUi() {
    val systemUiController = rememberSystemUiController()
    val systemUiColor = Color.Transparent
    val systemUiDarkIcons = MaterialTheme.colors.isLight
    SideEffect {
        systemUiController.setSystemBarsColor(
            color = systemUiColor,
            darkIcons = systemUiDarkIcons,
        )
    }
}
