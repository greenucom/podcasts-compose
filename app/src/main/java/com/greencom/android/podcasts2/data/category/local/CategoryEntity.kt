package com.greencom.android.podcasts2.data.category.local

import com.greencom.android.podcasts2.domain.category.Category
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CategoryEntity(

    @SerialName("id")
    val id: Int,

    @SerialName("name")
    val name: String,

) {

    fun toDomain(): Category = Category(
        id = id,
        name = name,
    )

    companion object {

        fun fromDomain(category: Category): CategoryEntity = CategoryEntity(
            id = category.id,
            name = category.name,
        )

    }

}
