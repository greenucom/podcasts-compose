package com.greencom.android.podcasts2.ui.screen.discover

import androidx.lifecycle.viewModelScope
import com.greencom.android.podcasts2.domain.category.usecase.GetSelectedTrendingCategoriesIdsUseCase
import com.greencom.android.podcasts2.domain.category.usecase.GetTrendingCategoriesUseCase
import com.greencom.android.podcasts2.domain.category.usecase.ToggleSelectedTrendingCategoryIdUseCase
import com.greencom.android.podcasts2.domain.podcast.TrendingPodcast
import com.greencom.android.podcasts2.domain.podcast.usecase.GetTrendingPodcastsPayload
import com.greencom.android.podcasts2.domain.podcast.usecase.GetTrendingPodcastsUseCase
import com.greencom.android.podcasts2.ui.common.BaseViewModel
import com.greencom.android.podcasts2.ui.screen.discover.model.SelectableTrendingCategory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiscoverViewModel @Inject constructor(
    private val getTrendingCategoriesUseCase: GetTrendingCategoriesUseCase,
    private val getSelectedTrendingCategoriesIdsUseCase: GetSelectedTrendingCategoriesIdsUseCase,
    private val toggleSelectedTrendingCategoryIdUseCase: ToggleSelectedTrendingCategoryIdUseCase,
    private val getTrendingPodcastsUseCase: GetTrendingPodcastsUseCase,
) : BaseViewModel() {

    private val _viewState = MutableStateFlow(ViewState())
    val viewState = _viewState.asStateFlow()

    private var trendingPodcastsJob: Job? = null

    init {
        loadTrendingPodcasts()
    }

    private fun loadTrendingPodcasts() = viewModelScope.launch {
        val trendingCategories = getTrendingCategoriesUseCase(Unit)
        getSelectedTrendingCategoriesIdsUseCase(Unit).collect { ids ->
            val categories = trendingCategories.map { category ->
                SelectableTrendingCategory(
                    isSelected = category.id in ids,
                    category = category,
                )
            }
            _viewState.update { it.copy(trendingCategories = categories) }

            loadTrendingPodcastsForSelectedCategories(categories)
        }
    }

    private fun loadTrendingPodcastsForSelectedCategories(
        categories: List<SelectableTrendingCategory>
    ) {
        trendingPodcastsJob?.cancel()
        trendingPodcastsJob = viewModelScope.launch {
            val selectedCategories = categories
                .filter { it.isSelected }
                .map { it.category }

            val max = if (selectedCategories.isEmpty()) {
                TrendingPodcastCountMaxValue
            } else {
                val value = selectedCategories.size * TrendingPodcastCountPerCategory
                value.coerceIn(TrendingPodcastCountMinValue, TrendingPodcastCountMaxValue)
            }

            val params = GetTrendingPodcastsPayload(
                max = max,
                inCategories = selectedCategories,
            )
            getTrendingPodcastsUseCase(params)
                .onSuccess(::onLoadTrendingPodcastsSuccess)
                .onFailure { /* TODO */ }
        }
    }

    fun onTrendingCategoryClicked(selectableCategory: SelectableTrendingCategory) {
        viewModelScope.launch {
            val id = selectableCategory.category.id
            toggleSelectedTrendingCategoryIdUseCase(id)
        }
    }

    private fun onLoadTrendingPodcastsSuccess(podcasts: List<TrendingPodcast>) {
        _viewState.update { it.copy(trendingPodcasts = podcasts) }
    }

    data class ViewState(
        val trendingCategories: List<SelectableTrendingCategory> = emptyList(),
        val trendingPodcasts: List<TrendingPodcast> = emptyList(),
    )

    companion object {
        private const val TrendingPodcastCountMaxValue = 40
        private const val TrendingPodcastCountMinValue = 15
        private const val TrendingPodcastCountPerCategory = 5
    }

}
