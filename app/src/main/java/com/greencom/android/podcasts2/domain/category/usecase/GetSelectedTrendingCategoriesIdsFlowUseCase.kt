package com.greencom.android.podcasts2.domain.category.usecase

import com.greencom.android.podcasts2.base.clean.SimpleUseCase
import com.greencom.android.podcasts2.data.category.CategoryRepository
import com.greencom.android.podcasts2.di.IODispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetSelectedTrendingCategoriesIdsUseCase @Inject constructor(
    @IODispatcher private val dispatcher: CoroutineDispatcher,
    private val categoryRepository: CategoryRepository,
) : SimpleUseCase<Unit, Flow<Set<Int>>>() {

    override fun invoke(params: Unit): Flow<Set<Int>> {
        return categoryRepository.getSelectedTrendingCategoriesIds().flowOn(dispatcher)
    }

}
