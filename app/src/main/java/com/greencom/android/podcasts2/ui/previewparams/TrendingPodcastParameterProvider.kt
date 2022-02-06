package com.greencom.android.podcasts2.ui.previewparams

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.greencom.android.podcasts2.domain.podcasts.TrendingPodcast

class TrendingPodcastParameterProvider : PreviewParameterProvider<TrendingPodcast> {

    override val values: Sequence<TrendingPodcast>
        get() = sequenceOf(
            createShortPodcast(),
            createLongPodcast(),
        )

    private fun createShortPodcast(): TrendingPodcast = TrendingPodcast(
        id = 1,
        url = "",
        title = "Short title",
        description = "Short description",
        author = "Short author",
        image = "",
        newestItemPublishedTime = 0,
        trendScore = 0,
        language = "",
        categories = emptyList(),
        isSubscribed = false,
    )

    private fun createLongPodcast(): TrendingPodcast = createShortPodcast().copy(
        title = "Long long long long long long long long long long long title",
        description = "Long long long long long long long long long long long long description",
        author = "Long long long long long long long author",
        isSubscribed = true,
    )

}
