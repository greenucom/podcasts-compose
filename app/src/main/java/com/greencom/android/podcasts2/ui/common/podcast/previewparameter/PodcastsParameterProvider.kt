package com.greencom.android.podcasts2.ui.common.podcast.previewparameter

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.greencom.android.podcasts2.domain.podcast.Podcast

class PodcastsParameterProvider : PreviewParameterProvider<List<Podcast>> {

    override val values: Sequence<List<Podcast>>
        get() = sequenceOf(trendingPodcasts())

    companion object {

        fun trendingPodcasts(): List<Podcast> {
            return listOf(
                PodcastParameterProvider.shortTrendingPodcast(),
                PodcastParameterProvider.longTrendingPodcast(),
            )
        }

    }

}
