package com.greencom.android.podcasts2.ui.screen.discover.component

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp

object PodcastCardUtils {

    val MaxWidthDp = 320.dp
    const val MaxWidthPercentOfScreen = 0.7f

    private const val TitleSuffix = " tiredtiredtiredtiredtiredtiredtiredtiredtired"

    fun handlePodcastTitle(title: String): AnnotatedString = buildAnnotatedString {
        append(title)

        withStyle(style = SpanStyle(color = Color.Transparent)) {
            append(TitleSuffix)
        }
    }

}
