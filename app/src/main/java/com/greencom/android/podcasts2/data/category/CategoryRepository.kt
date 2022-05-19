package com.greencom.android.podcasts2.data.category

import com.greencom.android.podcasts2.domain.category.Category
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoryRepository @Inject constructor(
    private val localDataSource: CategoryLocalDataSource,
) {

    fun getTrendingCategories(): List<Category> {
        return localDataSource.getTrendingCategories()
    }

    fun getSelectedTrendingCategoriesIds(): Flow<Set<Int>> {
        return localDataSource.getSelectedTrendingCategoriesIds()
    }

    suspend fun toggleTrendingCategory(category: Category) {
        localDataSource.toggleTrendingCategory(category)
    }

}
