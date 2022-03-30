package com.greencom.android.podcasts2.ui.screen.discover

import androidx.lifecycle.viewModelScope
import com.greencom.android.podcasts2.domain.category.Category
import com.greencom.android.podcasts2.domain.category.usecase.GetSelectedTrendingCategoriesIdsUseCase
import com.greencom.android.podcasts2.domain.category.usecase.GetTrendingCategoriesUseCase
import com.greencom.android.podcasts2.domain.category.usecase.ToggleSelectableTrendingCategoryUseCase
import com.greencom.android.podcasts2.domain.podcast.Podcast
import com.greencom.android.podcasts2.domain.podcast.usecase.RequestTrendingPodcastsUseCase
import com.greencom.android.podcasts2.domain.podcast.usecase.TrendingPodcastsFlowUseCase
import com.greencom.android.podcasts2.domain.podcast.usecase.UpdatePodcastSubscriptionUseCase
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
    private val requestTrendingPodcastsUseCase: RequestTrendingPodcastsUseCase,
    private val trendingPodcastsFlowUseCase: TrendingPodcastsFlowUseCase,
    private val updatePodcastSubscriptionUseCase: UpdatePodcastSubscriptionUseCase,
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
        loadTrendingCategoriesAndRequestPodcasts()
        loadTrendingPodcasts()
    }

    private fun loadTrendingCategoriesAndRequestPodcasts() = viewModelScope.launch {
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

            requestTrendingPodcastsForSelectedCategories(selectedCategories)
        }
    }

    private fun requestTrendingPodcastsForSelectedCategories(
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

            val params = RequestTrendingPodcastsUseCase.Params(
                max = max,
                inCategories = selectedCategories,
            )
            requestTrendingPodcastsUseCase(params)
                .onFailure(::onRequestTrendingPodcastsFailure)
        }
    }

    private fun onRequestTrendingPodcastsFailure(e: Throwable) {
        if (e !is CancellationException) {
            _trendingPodcastsState.update { TrendingPodcastsState.Error }
        }
    }

    private fun loadTrendingPodcasts() = viewModelScope.launch {
        trendingPodcastsFlowUseCase(Unit).collect { podcasts ->
            if (podcasts.isNotEmpty()) {
                val state = TrendingPodcastsState.Success(podcasts)
                _trendingPodcastsState.update { state }
            }
        }
    }

    fun onSelectableTrendingCategoryClicked(selectableCategory: SelectableItem<Category>) {
        viewModelScope.launch {
            val category = selectableCategory.item
            toggleSelectableTrendingCategoryUseCase(category)
        }
    }

    fun onSubscribedChanged(podcast: Podcast) = viewModelScope.launch {
        updatePodcastSubscriptionUseCase(podcast)
    }

    fun onTryAgainClicked() {
        _trendingPodcastsState.update { TrendingPodcastsState.Loading }

        val selectedCategories = trendingCategories.value
            .filter { it.isSelected }
            .map { it.item }
        requestTrendingPodcastsForSelectedCategories(selectedCategories)
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
