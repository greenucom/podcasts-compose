package com.greencom.android.podcasts2.domain.category.usecase

import com.greencom.android.podcasts2.base.clean.SimpleUseCase
import com.greencom.android.podcasts2.data.category.CategoryRepository
import com.greencom.android.podcasts2.domain.category.Category
import javax.inject.Inject

// TODO: Remove
class GetTrendingCategoriesUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository,
) : SimpleUseCase<Unit, List<Category>>() {

    override fun invoke(params: Unit): List<Category> {
        return categoryRepository.getTrendingCategories()
    }

}
