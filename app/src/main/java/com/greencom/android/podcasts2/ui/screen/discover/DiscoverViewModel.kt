package com.greencom.android.podcasts2.ui.screen.discover

import androidx.lifecycle.viewModelScope
import com.greencom.android.podcasts2.domain.category.TrendingCategory
import com.greencom.android.podcasts2.domain.category.usecase.GetSelectedTrendingCategoriesIdsUseCase
import com.greencom.android.podcasts2.domain.category.usecase.GetTrendingCategoriesUseCase
import com.greencom.android.podcasts2.domain.category.usecase.ToggleSelectedTrendingCategoryIdUseCase
import com.greencom.android.podcasts2.ui.common.BaseViewModel
import com.greencom.android.podcasts2.ui.screen.discover.model.SelectableTrendingCategory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiscoverViewModel @Inject constructor(
    getTrendingCategoriesUseCase: GetTrendingCategoriesUseCase,
    getSelectedTrendingCategoriesIdsUseCase: GetSelectedTrendingCategoriesIdsUseCase,
    private val toggleSelectedTrendingCategoryIdUseCase: ToggleSelectedTrendingCategoryIdUseCase,
) : BaseViewModel() {

    private val trendingCategories = MutableStateFlow<List<TrendingCategory>>(
        getTrendingCategoriesUseCase(Unit)
    )
    private val selectedTrendingCategoriesIds: Flow<Set<Int>> =
        getSelectedTrendingCategoriesIdsUseCase(Unit)
    val selectableTrendingCategories: Flow<List<SelectableTrendingCategory>> = combine(
        trendingCategories,
        selectedTrendingCategoriesIds,
    ) { categories, selectedCategoriesIds ->
        categories.map { category ->
            SelectableTrendingCategory(
                isSelected = category.id in selectedCategoriesIds,
                category = category,
            )
        }
    }

    fun onTrendingCategoryClicked(selectableCategory: SelectableTrendingCategory) {
        viewModelScope.launch {
            val id = selectableCategory.category.id
            toggleSelectedTrendingCategoryIdUseCase(id)
        }
    }

}
