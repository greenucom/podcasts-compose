package com.greencom.android.podcasts2.ui.common.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.greencom.android.podcasts2.ui.common.model.category.CategoryUiModel

class CategoryPreviewParameterProvider : PreviewParameterProvider<CategoryUiModel> {

    override val values: Sequence<CategoryUiModel>
        get() = sequenceOf(news(), society(), trueCrime())

    companion object {
        fun news(): CategoryUiModel = CategoryUiModel(
            id = 55,
            name = "News",
        )

        fun society(): CategoryUiModel = CategoryUiModel(
            id = 77,
            name = "Society",
        )

        fun trueCrime(): CategoryUiModel = CategoryUiModel(
            id = 103,
            name = "True Crime",
        )
    }

}
