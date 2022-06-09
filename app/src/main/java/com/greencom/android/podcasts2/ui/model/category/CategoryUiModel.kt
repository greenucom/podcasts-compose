package com.greencom.android.podcasts2.ui.model.category

import androidx.compose.runtime.Immutable
import com.greencom.android.podcasts2.domain.category.Category

@Immutable
data class CategoryUiModel(
    val id: Int,
    val name: String,
) {

    companion object {

        fun fromCategory(category: Category): CategoryUiModel = CategoryUiModel(
            id = category.id,
            name = category.name,
        )

    }

}
