package com.greencom.android.podcasts2.ui.screen.discover.previewparameter

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.greencom.android.podcasts2.domain.category.TrendingCategory
import com.greencom.android.podcasts2.ui.common.SelectableItem

class SelectableTrendingCategoriesParameterProvider :
    PreviewParameterProvider<List<SelectableItem<TrendingCategory>>> {

    override val values: Sequence<List<SelectableItem<TrendingCategory>>>
        get() = sequenceOf(trendingCategories())

    companion object {

        fun trendingCategories(): List<SelectableItem<TrendingCategory>> {
            val news = TrendingCategory(1, "News", "News")
            val society = TrendingCategory(2, "Society", "Society")
            val education = TrendingCategory(3, "Education", "Education")
            val sports = TrendingCategory(4, "Sports", "Sports")

            return listOf(
                SelectableItem(isSelected = false, item = news),
                SelectableItem(isSelected = true, item = society),
                SelectableItem(isSelected = false, item = education),
                SelectableItem(isSelected = true, item = sports),
            )
        }

    }

}
