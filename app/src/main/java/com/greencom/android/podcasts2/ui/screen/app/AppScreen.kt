package com.greencom.android.podcasts2.ui.screen.app

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.greencom.android.podcasts2.ui.screen.app.component.AppNavHost
import com.greencom.android.podcasts2.ui.screen.app.component.NavigationBarCustomRespectingWindowInsets
import com.greencom.android.podcasts2.ui.screen.app.component.NavigationRailCustomRespectingWindowInsets
import com.greencom.android.podcasts2.ui.screen.app.component.PermanentNavigationDrawerCustomRespectingWindowInsets

private val ScreenWidthForPermanentNavigationDrawer = 1240.dp

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScreen(
    windowSizeClass: WindowSizeClass,
    modifier: Modifier = Modifier,
) {
    val screenState = rememberAppScreenState()

    Scaffold(
        modifier = modifier,
        bottomBar = {
            if (windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact) {
                NavigationBarCustomRespectingWindowInsets(navController = screenState.navController)
            }
        },
    ) {

        if (windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact) {
            AppNavHost(navController = screenState.navController)
        } else {
            val screenWidthDp = LocalConfiguration.current.screenWidthDp.dp
            if (screenWidthDp < ScreenWidthForPermanentNavigationDrawer) {
                Row {
                    NavigationRailCustomRespectingWindowInsets(navController = screenState.navController)
                    AppNavHost(navController = screenState.navController)
                }
            } else {
                PermanentNavigationDrawerCustomRespectingWindowInsets(
                    navController = screenState.navController,
                ) {
                    AppNavHost(navController = screenState.navController)
                }
            }
        }
    }
}
