package com.greencom.android.podcasts2.ui.screen.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

class AppScreenState(val navController: NavHostController)

@Composable
fun rememberAppScreenState(
    navController: NavHostController = rememberNavController(),
) = remember (navController) {
    AppScreenState(navController = navController)
}
