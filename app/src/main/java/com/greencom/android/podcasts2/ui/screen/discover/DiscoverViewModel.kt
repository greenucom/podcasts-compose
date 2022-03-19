package com.greencom.android.podcasts2.ui.screen.discover

import androidx.lifecycle.viewModelScope
import com.greencom.android.podcasts2.domain.category.Category
import com.greencom.android.podcasts2.domain.category.usecase.GetSelectedTrendingCategoriesIdsUseCase
import com.greencom.android.podcasts2.domain.category.usecase.GetTrendingCategoriesUseCase
import com.greencom.android.podcasts2.domain.category.usecase.ToggleSelectableTrendingCategoryUseCase
import com.greencom.android.podcasts2.domain.podcast.Podcast
import com.greencom.android.podcasts2.domain.podcast.usecase.GetTrendingPodcastsPayload
import com.greencom.android.podcasts2.domain.podcast.usecase.GetTrendingPodcastsUseCase
import com.greencom.android.podcasts2.ui.common.BaseViewModel
import com.greencom.android.podcasts2.ui.common.SelectableItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
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
    private val toggleSelectableTrendingCategoryUseCase: ToggleSelectableTrendingCategoryUseCase,
    private val getTrendingPodcastsUseCase: GetTrendingPodcastsUseCase,
) : BaseViewModel() {

    private val _recommendedPodcastsState =
        MutableStateFlow<RecommendedPodcastsState>(RecommendedPodcastsState.Loading)
    val recommendedPodcastsState = _recommendedPodcastsState.asStateFlow()

    private val _trendingCategories =
        MutableStateFlow<List<SelectableItem<Category>>>(emptyList())
    val trendingCategories = _trendingCategories.asStateFlow()

    private val _trendingPodcastsState =
        MutableStateFlow<TrendingPodcastsState>(TrendingPodcastsState.Loading)
    val trendingPodcastsState = _trendingPodcastsState.asStateFlow()

    private var trendingPodcastsJob: Job? = null

    init {
        loadTrendingPodcasts()
    }

    private fun loadTrendingPodcasts() = viewModelScope.launch {
        val trendingCategories = getTrendingCategoriesUseCase(Unit)
        getSelectedTrendingCategoriesIdsUseCase(Unit).collect { ids ->
            val categories = trendingCategories.map { category ->
                SelectableItem(
                    isSelected = category.id in ids,
                    item = category,
                )
            }
            _trendingCategories.update { categories }

            val selectedCategories = categories
                .filter { it.isSelected }
                .map { it.item }

            loadTrendingPodcastsForSelectedCategories(selectedCategories)
        }
    }

    private fun loadTrendingPodcastsForSelectedCategories(
        selectedCategories: List<Category>
    ) {
        trendingPodcastsJob?.cancel()
        trendingPodcastsJob = viewModelScope.launch {
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
                .onFailure(::onLoadTrendingPodcastsFailure)
        }
    }

    private fun onLoadTrendingPodcastsSuccess(podcasts: List<Podcast>) {
        _trendingPodcastsState.update { TrendingPodcastsState.Success(podcasts) }
    }

    private fun onLoadTrendingPodcastsFailure(e: Throwable) {
        if (e !is CancellationException) {
            _trendingPodcastsState.update { TrendingPodcastsState.Error }
        }
    }

    fun onSelectableTrendingCategoryClicked(selectableCategory: SelectableItem<Category>) {
        viewModelScope.launch {
            val category = selectableCategory.item
            toggleSelectableTrendingCategoryUseCase(category)
        }
    }

    fun onTryAgainClicked() {
        _trendingPodcastsState.update { TrendingPodcastsState.Loading }

        val selectedCategories = trendingCategories.value
            .filter { it.isSelected }
            .map { it.item }
        loadTrendingPodcastsForSelectedCategories(selectedCategories)
    }

    sealed interface RecommendedPodcastsState {
        data class Success(val recommendedPodcasts: List<Podcast>) : RecommendedPodcastsState
        object Loading : RecommendedPodcastsState
    }

    sealed interface TrendingPodcastsState {
        data class Success(val trendingPodcasts: List<Podcast>) : TrendingPodcastsState
        object Loading : TrendingPodcastsState
        object Error : TrendingPodcastsState
    }

    companion object {
        private const val TrendingPodcastCountMaxValue = 40
        private const val TrendingPodcastCountMinValue = 15
        private const val TrendingPodcastCountPerCategory = 5
    }

}
