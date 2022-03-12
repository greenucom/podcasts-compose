package com.greencom.android.podcasts2.ui.screen.discover.previewparameter

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.greencom.android.podcasts2.domain.category.Category
import com.greencom.android.podcasts2.ui.common.SelectableItem

class SelectableTrendingCategoriesParameterProvider :
    PreviewParameterProvider<List<SelectableItem<Category>>> {

    override val values: Sequence<List<SelectableItem<Category>>>
        get() = sequenceOf(trendingCategories())

    companion object {

        fun trendingCategories(): List<SelectableItem<Category>> {
            val news = Category(1, "News", "News")
            val society = Category(2, "Society", "Society")
            val education = Category(3, "Education", "Education")
            val sports = Category(4, "Sports", "Sports")

            return listOf(
                SelectableItem(isSelected = false, item = news),
                SelectableItem(isSelected = true, item = society),
                SelectableItem(isSelected = false, item = education),
                SelectableItem(isSelected = true, item = sports),
            )
        }

    }

}
