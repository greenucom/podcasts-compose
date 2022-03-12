package com.greencom.android.podcasts2.data.category.remote.dto

import com.greencom.android.podcasts2.domain.category.Category
import com.greencom.android.podcasts2.domain.category.usecase.GetCategoryDisplayNamePayload
import com.greencom.android.podcasts2.domain.category.usecase.GetCategoryDisplayNameUseCase

typealias CategoriesDto = Map<Int, String>

fun CategoriesDto.toDomain(
    getCategoryDisplayNameUseCase: GetCategoryDisplayNameUseCase,
): List<Category> {
    val categories = mutableListOf<Category>()
    for ((id, name) in this) {
        val params = GetCategoryDisplayNamePayload(id)
        val displayName = getCategoryDisplayNameUseCase(params)
        val category = Category(id, name, displayName)
        categories.add(category)
    }
    return categories
}
