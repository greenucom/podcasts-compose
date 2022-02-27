package com.greencom.android.podcasts2.ui.screen.discover.previewparameter

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.greencom.android.podcasts2.domain.podcast.TrendingPodcast

class TrendingPodcastsParameterProvider : PreviewParameterProvider<List<TrendingPodcast>> {

    override val values: Sequence<List<TrendingPodcast>>
        get() = sequenceOf(trendingPodcasts())

    companion object {

        fun trendingPodcasts(): List<TrendingPodcast> {
            return listOf(
                TrendingPodcastParameterProvider.shortTrendingPodcast(),
                TrendingPodcastParameterProvider.longTrendingPodcast(),
            )
        }

    }

}
