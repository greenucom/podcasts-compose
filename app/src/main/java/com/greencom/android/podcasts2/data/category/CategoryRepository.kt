package com.greencom.android.podcasts2.data.category

import com.greencom.android.podcasts2.domain.category.TrendingCategory
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoryRepository @Inject constructor(
    private val localDataSource: CategoryLocalDataSource,
) {

    suspend fun toggleSelectedTrendingCategoryId(id: Int) {
        localDataSource.toggleSelectedTrendingCategoryId(id)
    }

    fun getSelectedTrendingCategoriesIds(): Flow<Set<Int>> {
        return localDataSource.getSelectedTrendingCategoriesIds()
    }

    fun getTrendingCategories(): List<TrendingCategory> {
        return localDataSource.getTrendingCategories()
    }

}
