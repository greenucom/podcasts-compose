package com.greencom.android.podcasts2.data.category.local

import com.greencom.android.podcasts2.domain.category.Category
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CategoryEntityDto(

    @SerialName("id")
    val id: Int,

    @SerialName("name")
    val name: String,

) {

    fun toCategory(): Category = Category(
        id = id,
        name = name,
    )

    companion object {

        fun fromCategory(category: Category): CategoryEntityDto = CategoryEntityDto(
            id = category.id,
            name = category.name,
        )

    }

}
