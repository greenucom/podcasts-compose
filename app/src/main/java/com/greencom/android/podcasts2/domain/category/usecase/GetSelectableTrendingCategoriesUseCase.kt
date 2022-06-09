package com.greencom.android.podcasts2.domain.category.usecase

import com.greencom.android.podcasts2.base.clean.FlowUseCase
import com.greencom.android.podcasts2.data.category.CategoryRepository
import com.greencom.android.podcasts2.di.IODispatcher
import com.greencom.android.podcasts2.domain.category.Category
import com.greencom.android.podcasts2.ui.common.SelectableItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetSelectableTrendingCategoriesUseCase @Inject constructor(
    @IODispatcher dispatcher: CoroutineDispatcher,
    private val categoryRepository: CategoryRepository,
) : FlowUseCase<Unit, List<SelectableItem<Category>>>(dispatcher) {

    override fun execute(params: Unit): Flow<Result<List<SelectableItem<Category>>>> {
        val trendingCategories = categoryRepository.getTrendingCategories()
        return categoryRepository.getSelectedTrendingCategoriesIds()
            .map { selectedCategoriesIds ->
                trendingCategories.map { category ->
                    SelectableItem(
                        item = category,
                        isSelected = category.id in selectedCategoriesIds,
                    )
                }
            }
            .map { Result.success(it) }
    }

}
