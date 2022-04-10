package com.greencom.android.podcasts2.ui.common

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf

val LocalAppContentPaddingValues = compositionLocalOf {
    PaddingValues()
}

@Composable
fun PaddingValues.addAppContentPaddingValues(): PaddingValues {
    val appContentPaddingValues = LocalAppContentPaddingValues.current
    return this.add(appContentPaddingValues)
}
