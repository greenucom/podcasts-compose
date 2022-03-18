package com.greencom.android.podcasts2.ui.common.previewparameter

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.greencom.android.podcasts2.domain.category.Category

class CategoriesParameterProvider : PreviewParameterProvider<List<Category>> {

    override val values: Sequence<List<Category>>
        get() = sequenceOf(categories())

    companion object {

        fun categories(): List<Category> {
            return listOf(
                Category(id = 1, name = "News"),
                Category(id = 2, name = "Society"),
            )
        }

    }

}
