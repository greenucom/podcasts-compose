package com.greencom.android.podcasts2.ui.common

import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun <T : Any> CrossfadeStable(
    targetState: T,
    modifier: Modifier = Modifier,
    animationSpec: FiniteAnimationSpec<Float> = tween(),
    content: @Composable (T) -> Unit,
) {
    val transition = updateTransition(targetState = targetState, label = "CrossfadeStable")
    transition.Crossfade(
        modifier = modifier,
        animationSpec = animationSpec,
        contentKey = { it::class.java.simpleName },
        content = content,
    )
}
