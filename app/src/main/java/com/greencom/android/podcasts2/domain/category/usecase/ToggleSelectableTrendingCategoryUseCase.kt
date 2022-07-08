package com.greencom.android.podcasts2.domain.category.usecase

import com.greencom.android.podcasts2.base.clean.UseCase
import com.greencom.android.podcasts2.data.category.CategoryRepository
import com.greencom.android.podcasts2.di.Dispatcher
import com.greencom.android.podcasts2.di.PodcastsDispatcher
import com.greencom.android.podcasts2.domain.category.Category
import kotlinx.coroutines.CoroutineDispatcher
import timber.log.Timber
import javax.inject.Inject

class ToggleSelectableTrendingCategoryUseCase @Inject constructor(
    @Dispatcher(PodcastsDispatcher.IO) dispatcher: CoroutineDispatcher,
    private val categoryRepository: CategoryRepository,
) : UseCase<Category, Unit>(dispatcher) {

    override suspend fun execute(params: Category) {
        Timber.i("Toggle selectable trending category ${params.name}")
        categoryRepository.toggleSelectableTrendingCategory(params)
    }

}
