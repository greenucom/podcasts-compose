package com.greencom.android.podcasts2.ui.screen.discover

import androidx.lifecycle.viewModelScope
import com.greencom.android.podcasts2.domain.category.TrendingCategory
import com.greencom.android.podcasts2.domain.category.usecase.GetSelectedTrendingCategoriesIdsUseCase
import com.greencom.android.podcasts2.domain.category.usecase.GetTrendingCategoriesUseCase
import com.greencom.android.podcasts2.domain.category.usecase.ToggleSelectedTrendingCategoryIdUseCase
import com.greencom.android.podcasts2.domain.podcast.IPodcast
import com.greencom.android.podcasts2.domain.podcast.TrendingPodcast
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
    private val toggleSelectedTrendingCategoryIdUseCase: ToggleSelectedTrendingCategoryIdUseCase,
    private val getTrendingPodcastsUseCase: GetTrendingPodcastsUseCase,
) : BaseViewModel() {

    private val _recommendedPodcastsState =
        MutableStateFlow<RecommendedPodcastsState>(RecommendedPodcastsState.Loading)
    val recommendedPodcastsState = _recommendedPodcastsState.asStateFlow()

    private val _trendingCategories =
        MutableStateFlow<List<SelectableItem<TrendingCategory>>>(emptyList())
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
        selectedCategories: List<TrendingCategory>
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

    private fun onLoadTrendingPodcastsSuccess(podcasts: List<TrendingPodcast>) {
        _trendingPodcastsState.update { TrendingPodcastsState.Success(podcasts) }
    }

    private fun onLoadTrendingPodcastsFailure(e: Throwable) {
        if (e !is CancellationException) {
            _trendingPodcastsState.update { TrendingPodcastsState.Error }
        }
    }

    fun onSelectableTrendingCategoryClicked(selectableCategory: SelectableItem<TrendingCategory>) {
        viewModelScope.launch {
            val id = selectableCategory.item.id
            toggleSelectedTrendingCategoryIdUseCase(id)
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
        data class Success(val recommendedPodcasts: List<IPodcast>) : RecommendedPodcastsState
        object Loading : RecommendedPodcastsState
    }

    sealed interface TrendingPodcastsState {
        data class Success(val trendingPodcasts: List<TrendingPodcast>) : TrendingPodcastsState
        object Loading : TrendingPodcastsState
        object Error : TrendingPodcastsState
    }

    companion object {
        private const val TrendingPodcastCountMaxValue = 40
        private const val TrendingPodcastCountMinValue = 15
        private const val TrendingPodcastCountPerCategory = 5
    }

}
