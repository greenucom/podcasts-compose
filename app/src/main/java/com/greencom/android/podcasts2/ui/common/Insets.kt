package com.greencom.android.podcasts2.ui.common

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.LocalWindowInsets

@Composable
fun animateImePadding(): State<Dp> {
    val isImeVisible = LocalWindowInsets.current.ime.isVisible
    val imePadding = if (isImeVisible) {
        with(LocalDensity.current) { LocalWindowInsets.current.ime.bottom.toDp() }
    } else {
        0.dp
    }
    return animateDpAsState(targetValue = imePadding)
}
