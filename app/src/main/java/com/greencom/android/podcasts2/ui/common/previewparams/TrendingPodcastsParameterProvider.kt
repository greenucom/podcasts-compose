package com.greencom.android.podcasts2.ui.common.previewparams

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.greencom.android.podcasts2.domain.podcasts.TrendingPodcast

class TrendingPodcastsParameterProvider : PreviewParameterProvider<List<TrendingPodcast>> {

    override val values: Sequence<List<TrendingPodcast>>
        get() = sequenceOf(
            listOf(
                TrendingPodcastParameterProvider.shortTrendingPodcast(),
                TrendingPodcastParameterProvider.longTrendingPodcast(),
            )
        )

}
