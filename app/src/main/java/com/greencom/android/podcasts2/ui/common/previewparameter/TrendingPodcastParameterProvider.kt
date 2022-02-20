package com.greencom.android.podcasts2.ui.common.previewparameter

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.greencom.android.podcasts2.domain.podcast.TrendingPodcast

class TrendingPodcastParameterProvider : PreviewParameterProvider<TrendingPodcast> {

    override val values: Sequence<TrendingPodcast>
        get() = sequenceOf(shortTrendingPodcast(), longTrendingPodcast())

    companion object {

        fun shortTrendingPodcast(): TrendingPodcast = TrendingPodcast(
            id = 1,
            title = "Lorem Ipsum",
            description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
            author = "Lorem Ipsum",
            image = "",
            isSubscribed = false,
        )

        fun longTrendingPodcast(): TrendingPodcast = shortTrendingPodcast().copy(
            id = 2,
            title = "Lorem Ipsum is simply dummy text of the printing and typesetting industry",
            description = "Lorem Ipsum is simply dummy text of the printing and typesetting " +
                    "industry. Lorem Ipsum has been the industry's standard dummy text ever " +
                    "since the 1500s, when an unknown printer took a galley of type and scrambled " +
                    "it to make a type specimen book.",
        )

    }

}