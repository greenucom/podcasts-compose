package com.greencom.android.podcasts2.data.category

import android.content.Context
import com.greencom.android.podcasts2.data.category.local.TrendingCategoryFactory
import com.greencom.android.podcasts2.domain.category.TrendingCategory
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class CategoryLocalDataSource @Inject constructor(
    @ApplicationContext private val context: Context,
    private val trendingCategoryFactory: TrendingCategoryFactory,
) {

    fun getTrendingCategories(): List<TrendingCategory> {
        return trendingCategoryFactory.getTrendingCategories(context)
    }

}
