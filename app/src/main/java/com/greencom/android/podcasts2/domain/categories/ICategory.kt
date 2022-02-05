package com.greencom.android.podcasts2.domain.categories

abstract class ICategory(open val id: Int, open val name: String)

fun List<ICategory>.toCategoriesString(): String = this.joinToString(",") { it.name }
