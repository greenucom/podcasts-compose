package com.greencom.android.podcasts2.ui.common

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.request.ImageRequest
import com.greencom.android.podcasts2.R

private const val DefaultAlpha = 1.0f

@Composable
fun PodcastsAsyncImage(
    url: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    placeholder: Painter? = null,
    error: Painter? = null,
    fallback: Painter? = null,
    onLoading: ((AsyncImagePainter.State.Loading) -> Unit)? = null,
    onSuccess: ((AsyncImagePainter.State.Success) -> Unit)? = null,
    onError: ((AsyncImagePainter.State.Error) -> Unit)? = null,
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Fit,
    alpha: Float = DefaultAlpha,
) {
    val defaultPlaceholderResId = if (MaterialTheme.colors.isLight) {
        R.drawable.vec_image_placeholder_light
    } else {
        R.drawable.vec_image_placeholder_dark
    }
    val defaultPlaceholder = painterResource(id = defaultPlaceholderResId)

    AsyncImage(
        modifier = modifier,
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .crossfade(true)
            .build(),
        contentDescription = contentDescription,
        placeholder = placeholder ?: defaultPlaceholder,
        error = error ?: defaultPlaceholder,
        fallback = fallback ?: defaultPlaceholder,
        onLoading = onLoading,
        onSuccess = onSuccess,
        onError = onError,
        alignment = alignment,
        contentScale = contentScale,
        alpha = alpha,
    )
}
