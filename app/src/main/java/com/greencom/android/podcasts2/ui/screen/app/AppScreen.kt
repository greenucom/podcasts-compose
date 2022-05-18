package com.greencom.android.podcasts2.ui.screen.app

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.greencom.android.podcasts2.ui.screen.app.component.AppNavHost
import com.greencom.android.podcasts2.ui.screen.app.component.NavigationBarCustomRespectingSystemNavigationBar
import com.greencom.android.podcasts2.ui.screen.app.component.NavigationRailCustomRespectingSystemBars

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
                NavigationBarCustomRespectingSystemNavigationBar(screenState.navController)
            }
        },
    ) {

        if (windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact) {
            AppNavHost(screenState.navController)
        } else {
            Row {
                if (windowSizeClass.widthSizeClass == WindowWidthSizeClass.Medium) {
                    NavigationRailCustomRespectingSystemBars(screenState.navController)
                } else {
                    // TODO: Add ModalNavigationDrawer
                }
                AppNavHost(screenState.navController)
            }
        }
    }
}
