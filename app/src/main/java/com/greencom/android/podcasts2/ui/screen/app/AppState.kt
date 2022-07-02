package com.greencom.android.podcasts2.ui.screen.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

class AppState(val navController: NavHostController)

@Composable
fun rememberAppState(
    navController: NavHostController = rememberNavController(),
) = remember (navController) {
    AppState(navController = navController)
}
