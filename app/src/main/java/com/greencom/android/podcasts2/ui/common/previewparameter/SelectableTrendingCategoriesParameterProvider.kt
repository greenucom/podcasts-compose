package com.greencom.android.podcasts2.ui.common.previewparameter

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.greencom.android.podcasts2.domain.category.TrendingCategory
import com.greencom.android.podcasts2.ui.screen.discover.model.SelectableTrendingCategory

class SelectableTrendingCategoriesParameterProvider :
    PreviewParameterProvider<List<SelectableTrendingCategory>> {

    override val values: Sequence<List<SelectableTrendingCategory>>
        get() {
            val news = TrendingCategory(1, "News", "News")
            val society = TrendingCategory(2, "Society", "Society")
            val education = TrendingCategory(3, "Education", "Education")
            val sports = TrendingCategory(4, "Sports", "Sports")

            return sequenceOf(
                listOf(
                    SelectableTrendingCategory(isSelected = false, category = news),
                    SelectableTrendingCategory(isSelected = true, category = society),
                    SelectableTrendingCategory(isSelected = false, category = education),
                    SelectableTrendingCategory(isSelected = true, category = sports),
                )
            )
        }

}
