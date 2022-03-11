package com.greencom.android.podcasts2.ui.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues
import com.greencom.android.podcasts2.ui.screen.app.component.BottomNavBarHeight

@Composable
fun rememberTotalBottomBarsHeight(): Dp {
    val paddingValues = rememberInsetsPaddingValues(
        insets = LocalWindowInsets.current.systemBars,
        additionalBottom = BottomNavBarHeight,
    )
    return paddingValues.calculateBottomPadding()
}
