package com.greencom.android.podcasts2.ui.common

import androidx.compose.runtime.Composable
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest

@Composable
fun rememberImagePainterWithCrossfadeAndPlaceholder(
    data: Any?,
    onExecute: ImagePainter.ExecuteCallback = ImagePainter.ExecuteCallback.Default,
    builder: ImageRequest.Builder.() -> Unit = {},
): ImagePainter = rememberImagePainter(
    data = data,
    onExecute = onExecute,
) {
    crossfade(true)
    // TODO: Add placeholders
    builder()
}
