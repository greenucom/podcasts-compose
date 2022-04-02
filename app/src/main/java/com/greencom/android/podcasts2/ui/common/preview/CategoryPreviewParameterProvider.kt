package com.greencom.android.podcasts2.ui.common.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.greencom.android.podcasts2.domain.category.Category

class CategoryPreviewParameterProvider : PreviewParameterProvider<Category> {

    override val values: Sequence<Category>
        get() = sequenceOf(news(), society(), trueCrime())

    companion object {
        fun news(): Category = Category(
            id = 55,
            name = "News",
        )

        fun society(): Category = Category(
            id = 77,
            name = "Society",
        )

        fun trueCrime(): Category = Category(
            id = 103,
            name = "True Crime",
        )
    }

}
