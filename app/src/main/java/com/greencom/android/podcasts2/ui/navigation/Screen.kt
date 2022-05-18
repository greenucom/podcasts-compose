package com.greencom.android.podcasts2.ui.navigation

sealed class Screen<Args>(val scheme: String) {

    abstract fun createRoute(args: Args): String

    object MyPodcasts : Screen<Unit>("myPodcasts") {
        override fun createRoute(args: Unit): String = scheme
    }

    object Discover : Screen<Unit>("discover") {
        override fun createRoute(args: Unit): String = scheme
    }

    object Library : Screen<Unit>("library") {
        override fun createRoute(args: Unit): String = scheme
    }

}
