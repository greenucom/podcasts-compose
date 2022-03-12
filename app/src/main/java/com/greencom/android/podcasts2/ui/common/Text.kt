@file:Suppress("unused")

package com.greencom.android.podcasts2.ui.common

import android.content.Context
import androidx.annotation.PluralsRes
import androidx.annotation.StringRes

sealed class Text {

    abstract fun getString(context: Context): String

    object Empty : Text() {
        override fun getString(context: Context): String = ""
    }

    class ResourceText(
        @StringRes val resId: Int,
        private vararg val args: Any = emptyArray(),
    ) : Text() {
        override fun getString(context: Context): String {
            return context.getString(resId, args)
        }
    }

    class PluralResourceText(
        @PluralsRes val resId: Int,
        private val count: Int,
        private vararg val args: Any = emptyArray(),
    ) : Text() {
        override fun getString(context: Context): String {
            return context.resources.getQuantityString(resId, count, args)
        }
    }

    class StringText(private val text: String) : Text() {
        override fun getString(context: Context): String = text
    }

}
