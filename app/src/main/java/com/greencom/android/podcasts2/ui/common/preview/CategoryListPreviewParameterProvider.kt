package com.greencom.android.podcasts2.ui.common.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.greencom.android.podcasts2.domain.category.Category

class CategoryListPreviewParameterProvider : PreviewParameterProvider<List<Category>> {

    override val values: Sequence<List<Category>>
        get() {
            val list = with(CategoryPreviewParameterProvider) {
                listOf(news(), society(), trueCrime())
            }
            return sequenceOf(list)
        }

}
