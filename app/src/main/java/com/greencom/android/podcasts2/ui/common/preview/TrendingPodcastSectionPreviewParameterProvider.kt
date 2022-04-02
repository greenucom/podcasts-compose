package com.greencom.android.podcasts2.ui.common.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.greencom.android.podcasts2.domain.category.Category
import com.greencom.android.podcasts2.domain.podcast.Podcast
import com.greencom.android.podcasts2.ui.common.SelectableItem

class TrendingPodcastSectionPreviewParameterProvider :
    PreviewParameterProvider<TrendingPodcastSectionParameters> {

    override val values: Sequence<TrendingPodcastSectionParameters>
        get() = sequenceOf(parameters())

    companion object {
        fun parameters(): TrendingPodcastSectionParameters {
            val selectableCategories =
                SelectableCategoryListPreviewParameterProvider.selectableCategories()
            val podcasts = PodcastListPreviewParameterProvider.podcasts()
            return TrendingPodcastSectionParameters(
                selectableCategories = selectableCategories,
                trendingPodcasts = podcasts,
            )
        }
    }

}

class TrendingPodcastSectionParameters(
    val selectableCategories: List<SelectableItem<Category>>,
    val trendingPodcasts: List<Podcast>,
)
