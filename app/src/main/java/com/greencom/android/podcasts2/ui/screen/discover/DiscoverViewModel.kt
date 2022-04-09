package com.greencom.android.podcasts2.ui.screen.discover

import androidx.lifecycle.viewModelScope
import com.greencom.android.podcasts2.domain.category.Category
import com.greencom.android.podcasts2.domain.podcast.Podcast
import com.greencom.android.podcasts2.domain.podcast.usecase.RequestTrendingPodcastsUseCase
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
    private val interactor: DiscoverInteractor,
) : BaseViewModel<DiscoverViewModel.ViewState, DiscoverViewModel.ViewEvent>() {

    override val initialViewState = ViewState.None

    private val _recommendedPodcastsState =
        MutableStateFlow<RecommendedPodcastsState>(RecommendedPodcastsState.Loading)
    val recommendedPodcastsState = _recommendedPodcastsState.asStateFlow()

    private val _selectableCategories =
        MutableStateFlow<List<SelectableItem<Category>>>(emptyList())
    val selectableCategories = _selectableCategories.asStateFlow()

    private val _trendingPodcastsState =
        MutableStateFlow<TrendingPodcastsState>(TrendingPodcastsState.Loading)
    val trendingPodcastsState = _trendingPodcastsState.asStateFlow()

    private var trendingPodcastsJob: Job? = null

    init {
        loadTrendingCategoriesAndRequestTrendingPodcasts()
        loadTrendingPodcasts()
    }

    private fun loadTrendingCategoriesAndRequestTrendingPodcasts() = viewModelScope.launch {
        val trendingCategories = interactor.getTrendingCategoriesUseCase(Unit)
        interactor.getSelectedTrendingCategoriesIdsUseCase(Unit).collect { ids ->
            val categories = trendingCategories.map { category ->
                SelectableItem(
                    isSelected = category.id in ids,
                    item = category,
                )
            }
            _selectableCategories.update { categories }

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
            interactor.requestTrendingPodcastsUseCase(params)
                .onFailure(::onRequestTrendingPodcastsFailure)
        }
    }

    private fun onRequestTrendingPodcastsFailure(e: Throwable) {
        if (e !is CancellationException) {
            _trendingPodcastsState.update { TrendingPodcastsState.Error }
        }
    }

    private fun loadTrendingPodcasts() = viewModelScope.launch {
        interactor.getTrendingPodcastsFlowUseCase(Unit).collect { podcasts ->
            val state = if (podcasts.isEmpty()) {
                TrendingPodcastsState.Loading
            } else {
                TrendingPodcastsState.Success(podcasts)
            }
            _trendingPodcastsState.update { state }
        }
    }

    fun onSelectableCategoryClicked(category: Category) {
        viewModelScope.launch {
            interactor.toggleSelectableTrendingCategoryUseCase(category)
        }
    }

    fun onSubscribedChanged(podcast: Podcast) = viewModelScope.launch {
        interactor.updatePodcastSubscriptionUseCase(podcast)
    }

    fun onTryAgainClicked() {
        _trendingPodcastsState.update { TrendingPodcastsState.Loading }

        val selectedCategories = selectableCategories.value
            .filter { it.isSelected }
            .map { it.item }
        requestTrendingPodcastsForSelectedCategories(selectedCategories)
    }

    sealed interface ViewState {
        object None : ViewState
    }

    sealed interface RecommendedPodcastsState {
        data class Success(val podcasts: List<Podcast>) : RecommendedPodcastsState
        object Loading : RecommendedPodcastsState
    }

    sealed interface TrendingPodcastsState {
        data class Success(val podcasts: List<Podcast>) : TrendingPodcastsState
        object Loading : TrendingPodcastsState
        object Error : TrendingPodcastsState
    }

    sealed interface ViewEvent

    companion object {
        private const val TrendingPodcastCountMaxValue = 40
        private const val TrendingPodcastCountMinValue = 15
        private const val TrendingPodcastCountPerCategory = 5
    }

}
