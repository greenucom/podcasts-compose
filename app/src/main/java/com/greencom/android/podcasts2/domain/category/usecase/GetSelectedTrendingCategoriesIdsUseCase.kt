package com.greencom.android.podcasts2.domain.category.usecase

import com.greencom.android.podcasts2.base.clean.FlowUseCase
import com.greencom.android.podcasts2.data.category.CategoryRepository
import com.greencom.android.podcasts2.di.IODispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetSelectedTrendingCategoriesIdsUseCase @Inject constructor(
    @IODispatcher dispatcher: CoroutineDispatcher,
    private val categoryRepository: CategoryRepository,
) : FlowUseCase<Unit, Set<Int>>(dispatcher) {

    override fun execute(params: Unit): Flow<Result<Set<Int>>> {
        return categoryRepository.getSelectedTrendingCategoriesIds()
            .map { Result.success(it) }
    }

}
