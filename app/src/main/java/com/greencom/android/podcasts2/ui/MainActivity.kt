package com.greencom.android.podcasts2.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.greencom.android.podcasts2.ui.common.screenbehavior.LocalScreenBehaviorController
import com.greencom.android.podcasts2.ui.common.screenbehavior.ScreenBehavior
import com.greencom.android.podcasts2.ui.common.screenbehavior.ScreenBehaviorController
import com.greencom.android.podcasts2.ui.screen.app.AppScreen
import com.greencom.android.podcasts2.ui.theme.PodcastsTheme
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            TransparentSystemBars()

            val currentScreenBehavior = remember {
                mutableStateOf(ScreenBehavior.Default)
            }
            val screenBehaviorController = remember {
                object : ScreenBehaviorController {
                    override val currentScreenBehaviorAsState: State<ScreenBehavior>
                        get() = currentScreenBehavior

                    override fun setScreenBehavior(screenBehavior: ScreenBehavior) {
                        currentScreenBehavior.value = screenBehavior
                    }

                    override fun removeScreenBehavior(screenBehavior: ScreenBehavior) {
                        if (currentScreenBehavior.value == screenBehavior) {
                            currentScreenBehavior.value = ScreenBehavior.Default
                        }
                    }
                }
            }

            PodcastsTheme {
                Surface {
                    CompositionLocalProvider(
                        LocalScreenBehaviorController provides screenBehaviorController,
                    ) {
                        val windowSizeClass = calculateWindowSizeClass(activity = this)
                        AppScreen(windowSizeClass = windowSizeClass)
                    }
                }
            }
        }
    }
}

@Composable
fun TransparentSystemBars() {
    val systemUiController = rememberSystemUiController()
    val darkIcons = !isSystemInDarkTheme()

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = darkIcons,
        )
    }
}
