package com.greencom.android.podcasts2.ui.previewparameter.category

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.greencom.android.podcasts2.ui.model.category.CategoryUiModel

class CategoryUiModelListPreviewParameterProvider : PreviewParameterProvider<List<CategoryUiModel>> {

    override val values: Sequence<List<CategoryUiModel>>
        get() = sequenceOf(list())

    private fun list(): List<CategoryUiModel> {
        return CategoryUiModelPreviewParameterProvider().values.toList()
    }

}
