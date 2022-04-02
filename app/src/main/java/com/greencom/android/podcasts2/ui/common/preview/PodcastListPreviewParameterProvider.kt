package com.greencom.android.podcasts2.ui.common.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.greencom.android.podcasts2.domain.podcast.Podcast

class PodcastListPreviewParameterProvider : PreviewParameterProvider<List<Podcast>> {

    override val values: Sequence<List<Podcast>>
        get() = sequenceOf(podcasts())

    companion object {
        fun podcasts(): List<Podcast> = with(PodcastPreviewParameterProvider) {
            listOf(shortPodcast(), longPodcast())
        }
    }

}
