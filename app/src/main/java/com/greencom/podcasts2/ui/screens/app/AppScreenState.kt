package com.greencom.podcasts2.ui.screens.app

import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

class AppScreenState(
    val scaffoldState: ScaffoldState,
    val navController: NavHostController,
)

@Composable
fun rememberAppScreenState(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    navController: NavHostController = rememberNavController(),
) = remember(scaffoldState, navController) {
    AppScreenState(scaffoldState, navController)
}
