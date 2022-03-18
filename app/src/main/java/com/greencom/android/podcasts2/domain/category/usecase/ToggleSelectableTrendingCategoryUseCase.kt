package com.greencom.android.podcasts2.domain.category.usecase

import com.greencom.android.podcasts2.base.clean.UseCase
import com.greencom.android.podcasts2.data.category.CategoryRepository
import com.greencom.android.podcasts2.di.IODispatcher
import com.greencom.android.podcasts2.domain.category.Category
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class ToggleSelectableTrendingCategoryUseCase @Inject constructor(
    @IODispatcher dispatcher: CoroutineDispatcher,
    private val categoryRepository: CategoryRepository,
) : UseCase<Category, Unit>(dispatcher) {

    override suspend fun execute(params: Category) {
        categoryRepository.toggleSelectableTrendingCategory(params)
    }

}
