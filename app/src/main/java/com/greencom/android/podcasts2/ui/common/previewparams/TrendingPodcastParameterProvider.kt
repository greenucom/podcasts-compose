package com.greencom.android.podcasts2.ui.common.previewparams

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.greencom.android.podcasts2.domain.podcasts.TrendingPodcast

class TrendingPodcastParameterProvider : PreviewParameterProvider<TrendingPodcast> {

    override val values: Sequence<TrendingPodcast>
        get() = sequenceOf(short(), long())

    private fun short(): TrendingPodcast = TrendingPodcast(
        id = 1,
        title = "Lorem Ipsum",
        description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
        author = "Lorem Ipsum",
        image = "",
        isSubscribed = false,
    )

    private fun long(): TrendingPodcast = short().copy(
        title = "Lorem Ipsum is simply dummy text of the printing and typesetting industry",
        description = "Lorem Ipsum is simply dummy text of the printing and typesetting " +
                "industry. Lorem Ipsum has been the industry's standard dummy text ever " +
                "since the 1500s, when an unknown printer took a galley of type and scrambled " +
                "it to make a type specimen book.",
    )

}