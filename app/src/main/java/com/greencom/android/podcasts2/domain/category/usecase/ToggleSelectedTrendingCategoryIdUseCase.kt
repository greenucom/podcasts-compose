package com.greencom.android.podcasts2.domain.category.usecase

import com.greencom.android.podcasts2.clean.UseCase
import com.greencom.android.podcasts2.data.category.CategoryRepository
import com.greencom.android.podcasts2.di.IODispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class ToggleSelectedTrendingCategoryIdUseCase @Inject constructor(
    @IODispatcher dispatcher: CoroutineDispatcher,
    private val categoryRepository: CategoryRepository,
) : UseCase<Int, Unit>(dispatcher) {

    override suspend fun execute(params: Int) {
        categoryRepository.toggleSelectedTrendingCategoryId(params)
    }

}
