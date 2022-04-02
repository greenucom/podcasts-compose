package com.greencom.android.podcasts2.ui.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun AsyncImageCustom(
    data: Any?,
    modifier: Modifier = Modifier,
    contentDescription: String?,
    contentScale: ContentScale = ContentScale.Crop,
) {
    // TODO: Add placeholder, error and fallback
    AsyncImage(
        modifier = modifier,
        model = ImageRequest.Builder(LocalContext.current)
            .data(data)
            .crossfade(true)
            .build(),
        contentDescription = contentDescription,
        contentScale = contentScale,
    )
}
