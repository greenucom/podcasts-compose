package com.greencom.android.podcasts2.ui.screen.discover.previewparameter

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.greencom.android.podcasts2.domain.podcast.TrendingPodcast
import com.greencom.android.podcasts2.ui.screen.discover.model.SelectableTrendingCategory

class TrendingPodcastListParameterProvider :
    PreviewParameterProvider<TrendingPodcastListParameter> {

    override val values: Sequence<TrendingPodcastListParameter>
        get() = sequenceOf(trendingPodcastListParameter())

    companion object {

        fun trendingPodcastListParameter(): TrendingPodcastListParameter {
            val categories =
                SelectableTrendingCategoriesParameterProvider.selectableTrendingCategories()
            val podcasts = TrendingPodcastsParameterProvider.trendingPodcasts()

            return TrendingPodcastListParameter(
                selectableTrendingCategories = categories,
                trendingPodcasts = podcasts,
            )
        }

    }

}

class TrendingPodcastListParameter(
    val selectableTrendingCategories: List<SelectableTrendingCategory>,
    val trendingPodcasts: List<TrendingPodcast>,
)
