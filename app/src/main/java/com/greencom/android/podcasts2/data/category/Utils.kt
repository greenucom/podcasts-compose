package com.greencom.android.podcasts2.data.category

import com.greencom.android.podcasts2.domain.category.Category

fun List<Category>.toApiCategoriesString(): String {
    return this.joinToString(",") { it.name }
}
