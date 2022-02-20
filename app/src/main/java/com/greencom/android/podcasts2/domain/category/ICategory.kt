package com.greencom.android.podcasts2.domain.category

abstract class ICategory(open val id: Int, open val name: String)

fun List<ICategory>.toCategoriesString(): String = this.joinToString(",") { it.name }