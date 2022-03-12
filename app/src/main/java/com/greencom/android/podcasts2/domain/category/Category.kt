package com.greencom.android.podcasts2.domain.category

data class Category(
    val id: Int,
    val name: String,
    val displayName: String,
)

fun List<Category>.toCategoriesString(): String = this.joinToString(",") { it.name }
