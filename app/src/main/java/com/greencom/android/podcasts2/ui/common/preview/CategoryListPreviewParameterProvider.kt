package com.greencom.android.podcasts2.ui.common.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.greencom.android.podcasts2.ui.common.model.category.CategoryUiModel

class CategoryListPreviewParameterProvider : PreviewParameterProvider<List<CategoryUiModel>> {

    override val values: Sequence<List<CategoryUiModel>>
        get() = sequenceOf(categories())

    companion object {
        fun categories(): List<CategoryUiModel> = with(CategoryPreviewParameterProvider) {
            listOf(news(), society(), trueCrime())
        }
    }

}
