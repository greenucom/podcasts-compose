package com.greencom.android.podcasts2.data.category

import com.greencom.android.podcasts2.domain.category.Category

private const val SEPARATOR = ","

fun List<Category>.toApiCategoriesString(): String {
    return this.joinToString(SEPARATOR) { it.name }
}
