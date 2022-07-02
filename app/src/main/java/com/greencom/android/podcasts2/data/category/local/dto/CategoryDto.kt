package com.greencom.android.podcasts2.data.category.local.dto

import com.greencom.android.podcasts2.domain.category.Category
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CategoryDto(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
) {

    companion object {

        fun fromCategory(category: Category): CategoryDto = CategoryDto(
            id = category.id,
            name = category.name,
        )

    }

}
