package com.greencom.android.podcasts2.ui.navigation

sealed class Route<Args>(val scheme: String) {

    abstract fun createRoute(args: Args): String

    object Podcasts : Route<Unit>("podcasts") {
        override fun createRoute(args: Unit): String = scheme
    }

    object Discover : Route<Unit>("discover") {
        override fun createRoute(args: Unit): String = scheme
    }

    object Library : Route<Unit>("library") {
        override fun createRoute(args: Unit): String = scheme
    }

}
