package com.greencom.android.podcasts2.data.category.remote.dto

import com.greencom.android.podcasts2.domain.category.Category

typealias CategoriesDto = Map<Int, String>

fun CategoriesDto.toCategories(): List<Category> {
    val categories = mutableListOf<Category>()
    for ((id, name) in this) {
        val category = Category(id, name)
        categories.add(category)
    }
    return categories
}
