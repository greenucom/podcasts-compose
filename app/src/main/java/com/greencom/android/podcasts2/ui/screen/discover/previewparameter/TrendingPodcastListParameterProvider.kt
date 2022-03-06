package com.greencom.android.podcasts2.ui.screen.discover.previewparameter

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.greencom.android.podcasts2.domain.category.TrendingCategory
import com.greencom.android.podcasts2.domain.podcast.TrendingPodcast
import com.greencom.android.podcasts2.ui.common.SelectableItem

class TrendingPodcastListParameterProvider :
    PreviewParameterProvider<TrendingPodcastListParameter> {

    override val values: Sequence<TrendingPodcastListParameter>
        get() = sequenceOf(trendingPodcastListParameter())

    companion object {

        fun trendingPodcastListParameter(): TrendingPodcastListParameter {
            val categories =
                SelectableTrendingCategoriesParameterProvider.trendingCategories()
            val podcasts = TrendingPodcastsParameterProvider.trendingPodcasts()

            return TrendingPodcastListParameter(
                selectableTrendingCategories = categories,
                trendingPodcasts = podcasts,
            )
        }

    }

}

class TrendingPodcastListParameter(
    val selectableTrendingCategories: List<SelectableItem<TrendingCategory>>,
    val trendingPodcasts: List<TrendingPodcast>,
)
