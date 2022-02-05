package com.greencom.android.podcasts2.ui.navigation

sealed class Screen(val route: String) {

    object Home : Screen("home")
    object Discover : Screen("discover")
    object Library : Screen("library")

}
