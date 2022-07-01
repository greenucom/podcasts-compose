package com.greencom.android.podcasts2.ui.previewparameter.category

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.greencom.android.podcasts2.ui.model.category.CategoryUiModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

class CategoryUiModelListPreviewParameterProvider :
    PreviewParameterProvider<ImmutableList<CategoryUiModel>> {

    override val values: Sequence<ImmutableList<CategoryUiModel>>
        get() = sequenceOf(list())

    private fun list(): ImmutableList<CategoryUiModel> {
        return CategoryUiModelPreviewParameterProvider().values.toImmutableList()
    }

}
