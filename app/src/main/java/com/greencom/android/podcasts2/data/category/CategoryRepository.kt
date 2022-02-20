package com.greencom.android.podcasts2.data.category

import com.greencom.android.podcasts2.domain.category.TrendingCategory
import javax.inject.Inject

class CategoryRepository @Inject constructor(
    private val localDataSource: CategoryLocalDataSource,
) {

    fun getTrendingCategories(): List<TrendingCategory> {
        return localDataSource.getTrendingCategories()
    }

}
