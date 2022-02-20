package com.greencom.android.podcasts2.domain.category.usecase

import com.greencom.android.podcasts2.clean.SimpleUseCase
import com.greencom.android.podcasts2.data.category.CategoryRepository
import com.greencom.android.podcasts2.domain.category.TrendingCategory
import javax.inject.Inject

class GetTrendingCategoriesUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository,
) : SimpleUseCase<Unit, List<TrendingCategory>>() {

    override fun invoke(params: Unit): List<TrendingCategory> {
        return categoryRepository.getTrendingCategories()
    }

}
