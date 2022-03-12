package com.greencom.android.podcasts2.domain.category.usecase

import com.greencom.android.podcasts2.clean.SimpleUseCase
import com.greencom.android.podcasts2.data.category.CategoryRepository
import javax.inject.Inject

class GetCategoryDisplayNameUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository,
) : SimpleUseCase<GetCategoryDisplayNamePayload, String>() {

    override fun invoke(params: GetCategoryDisplayNamePayload): String {
        val categoryId = params.categoryId
        return categoryRepository.getCategoryDisplayName(categoryId)
    }

}

data class GetCategoryDisplayNamePayload(val categoryId: Int)
