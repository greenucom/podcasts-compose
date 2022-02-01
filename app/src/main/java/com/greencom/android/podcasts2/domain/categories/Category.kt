package com.greencom.android.podcasts2.domain.categories

data class Category(
    val id: Int,
    val name: String,
)

fun List<Category>.toCategoriesString(): String = this.joinToString(",") { it.name }
