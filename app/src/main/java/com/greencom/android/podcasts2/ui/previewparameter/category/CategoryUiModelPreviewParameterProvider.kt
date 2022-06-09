package com.greencom.android.podcasts2.ui.previewparameter.category

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.greencom.android.podcasts2.ui.model.category.CategoryUiModel

class CategoryUiModelPreviewParameterProvider : PreviewParameterProvider<CategoryUiModel> {

    override val values: Sequence<CategoryUiModel>
        get() = sequenceOf(arts(), books(), design(), fashion())

    private fun arts() = CategoryUiModel(
        id = 1,
        name = "Arts",
    )

    private fun books() = CategoryUiModel(
        id = 2,
        name = "Books",
    )

    private fun design() = CategoryUiModel(
        id = 3,
        name = "Design",
    )

    private fun fashion() = CategoryUiModel(
        id = 4,
        name = "Fashion",
    )

}
