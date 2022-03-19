package com.greencom.android.podcasts2.ui.screen.discover.previewparameter

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.greencom.android.podcasts2.domain.category.Category
import com.greencom.android.podcasts2.domain.podcast.Podcast
import com.greencom.android.podcasts2.ui.common.SelectableItem
import com.greencom.android.podcasts2.ui.common.podcast.previewparameter.PodcastsParameterProvider

class TrendingPodcastListParameterProvider :
    PreviewParameterProvider<TrendingPodcastListParameter> {

    override val values: Sequence<TrendingPodcastListParameter>
        get() = sequenceOf(trendingPodcastListParameter())

    companion object {

        fun trendingPodcastListParameter(): TrendingPodcastListParameter {
            val categories =
                SelectableTrendingCategoriesParameterProvider.trendingCategories()
            val podcasts = PodcastsParameterProvider.trendingPodcasts()

            return TrendingPodcastListParameter(
                selectableTrendingCategories = categories,
                trendingPodcasts = podcasts,
            )
        }

    }

}

class TrendingPodcastListParameter(
    val selectableTrendingCategories: List<SelectableItem<Category>>,
    val trendingPodcasts: List<Podcast>,
)
