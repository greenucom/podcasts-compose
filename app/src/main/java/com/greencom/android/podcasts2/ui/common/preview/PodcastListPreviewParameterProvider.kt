package com.greencom.android.podcasts2.ui.common.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.greencom.android.podcasts2.ui.common.model.podcast.PodcastUiModel

class PodcastListPreviewParameterProvider : PreviewParameterProvider<List<PodcastUiModel>> {

    override val values: Sequence<List<PodcastUiModel>>
        get() = sequenceOf(podcasts())

    companion object {
        fun podcasts(): List<PodcastUiModel> = with(PodcastPreviewParameterProvider) {
            listOf(shortPodcast(), longPodcast())
        }
    }

}
