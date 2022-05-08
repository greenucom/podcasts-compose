package com.greencom.android.podcasts2.ui.common.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.greencom.android.podcasts2.ui.common.SelectableItem
import com.greencom.android.podcasts2.ui.common.model.category.CategoryUiModel

class SelectableCategoryListPreviewParameterProvider :
    PreviewParameterProvider<List<SelectableItem<CategoryUiModel>>> {

    override val values: Sequence<List<SelectableItem<CategoryUiModel>>>
        get() = sequenceOf(selectableCategories())

    companion object {
        fun selectableCategories(): List<SelectableItem<CategoryUiModel>> {
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
