package com.greencom.android.podcasts2.utils

import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.ui.unit.DpSize

// TODO: Refactor when there is an alternative for calculateFromSize()
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
fun BoxWithConstraintsScope.toWindowSizeClass(): WindowSizeClass {
    val size = DpSize(this.maxWidth, this.maxHeight)
    return WindowSizeClass.calculateFromSize(size)
}
