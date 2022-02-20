package com.greencom.android.podcasts2.ui.common.previewparameter

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.greencom.android.podcasts2.domain.podcast.TrendingPodcast

class TrendingPodcastsParameterProvider : PreviewParameterProvider<List<TrendingPodcast>> {

    override val values: Sequence<List<TrendingPodcast>>
        get() = sequenceOf(
            listOf(
                TrendingPodcastParameterProvider.shortTrendingPodcast(),
                TrendingPodcastParameterProvider.longTrendingPodcast(),
            )
        )

}
