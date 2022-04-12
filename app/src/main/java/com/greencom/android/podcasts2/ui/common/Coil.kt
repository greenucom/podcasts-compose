package com.greencom.android.podcasts2.ui.common

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.greencom.android.podcasts2.R

@Composable
fun AsyncImageCustom(
    data: Any?,
    modifier: Modifier = Modifier,
    contentDescription: String?,
    contentScale: ContentScale = ContentScale.Crop,
) {
    val placeholderResId = if (isSystemInDarkTheme()) {
        R.drawable.img_image_placeholder_dark
    } else {
        R.drawable.img_image_placeholder_light
    }

    AsyncImage(
        modifier = modifier,
        model = ImageRequest.Builder(LocalContext.current)
            .data(data)
            .crossfade(true)
            .placeholder(placeholderResId)
            .error(placeholderResId)
            .fallback(placeholderResId)
            .build(),
        contentDescription = contentDescription,
        contentScale = contentScale,
    )
}
