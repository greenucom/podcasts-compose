package com.greencom.android.podcasts2.ui.screen.discover

import com.greencom.android.podcasts2.domain.category.TrendingCategory
import com.greencom.android.podcasts2.domain.category.usecase.GetTrendingCategoriesUseCase
import com.greencom.android.podcasts2.ui.common.BaseViewModel
import com.greencom.android.podcasts2.ui.screen.discover.model.SelectableTrendingCategory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class DiscoverViewModel @Inject constructor(
    getTrendingCategoriesUseCase: GetTrendingCategoriesUseCase,
) : BaseViewModel() {

    private val trendingCategories = MutableStateFlow<List<TrendingCategory>>(
        getTrendingCategoriesUseCase(Unit)
    )
    private val selectedTrendingCategoriesIds = MutableStateFlow<Set<Int>>(
        emptySet()
    )
    val selectableTrendingCategories = combine(
        trendingCategories,
        selectedTrendingCategoriesIds
    ) { categories, selectedCategoriesIds ->
        categories.map { category ->
            SelectableTrendingCategory(
                isSelected = category.id in selectedCategoriesIds,
                category = category,
            )
        }
    }

    fun onTrendingCategoryClicked(selectableCategory: SelectableTrendingCategory) {
        val id = selectableCategory.category.id
        selectedTrendingCategoriesIds.update { set ->
            if (id in set) set - id else set + id
        }
    }

}
