package com.greencom.android.podcasts2.domain.categories

data class Category(override val id: Int, override val name: String) : ICategory(id, name)