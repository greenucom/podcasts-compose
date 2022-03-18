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
            val news = Category(id = 1, name = "News")
            val society = Category(id = 2, "Society")
            val education = Category(id = 3, name = "Education")
            val sports = Category(id = 4, name = "Sports")

            return listOf(
                SelectableItem(isSelected = false, item = news),
                SelectableItem(isSelected = true, item = society),
                SelectableItem(isSelected = false, item = education),
                SelectableItem(isSelected = true, item = sports),
            )
        }

    }

}
