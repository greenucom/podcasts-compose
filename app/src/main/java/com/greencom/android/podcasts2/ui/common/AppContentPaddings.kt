package com.greencom.android.podcasts2.ui.common

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.dp

data class AppContentPaddings(val paddingValues: PaddingValues = PaddingValues(0.dp))

val LocalAppContentPaddings = compositionLocalOf {
    AppContentPaddings()
}

@Composable
fun PaddingValues.applyAppContentPaddings(): PaddingValues {
    val appContentPaddings = LocalAppContentPaddings.current.paddingValues
    return this.add(appContentPaddings)
}
