package com.greencom.android.podcasts2.ui.common.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.greencom.android.podcasts2.domain.category.Category
import com.greencom.android.podcasts2.ui.common.SelectableItem

class SelectableCategoryListPreviewParameterProvider :
    PreviewParameterProvider<List<SelectableItem<Category>>> {

    override val values: Sequence<List<SelectableItem<Category>>>
        get() = sequenceOf(selectableCategories())

    companion object {
        fun selectableCategories(): List<SelectableItem<Category>> {
            return CategoryListPreviewParameterProvider.categories()
                .mapIndexed { i, category ->
                    val isSelected = i % 2 != 0
                    SelectableItem(
                        item = category,
                        isSelected = isSelected,
                    )
                }
        }
    }

}
