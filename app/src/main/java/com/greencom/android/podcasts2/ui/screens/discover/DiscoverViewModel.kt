package com.greencom.android.podcasts2.ui.screens.discover

import androidx.lifecycle.viewModelScope
import com.greencom.android.podcasts2.domain.categories.TrendingCategory
import com.greencom.android.podcasts2.domain.podcasts.IPodcast
import com.greencom.android.podcasts2.domain.podcasts.TrendingPodcast
import com.greencom.android.podcasts2.domain.podcasts.payload.GetTrendingPodcastsPayload
import com.greencom.android.podcasts2.domain.podcasts.usecases.GetTrendingPodcastsUseCase
import com.greencom.android.podcasts2.ui.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiscoverViewModel @Inject constructor(
    private val getTrendingPodcastsUseCase: GetTrendingPodcastsUseCase,
) : BaseViewModel() {

    private val _viewState = MutableStateFlow<ViewState>(ViewState.InitialLoading)
    val viewState = _viewState.asStateFlow()

    private var loadTrendingPodcastsJob: Job? = null

    init {
        loadTrendingPodcasts()
    }

    fun onPodcastClicked(podcast: IPodcast) {

    }

    private fun loadTrendingPodcasts() {
        loadTrendingPodcastsJob?.cancel()
        loadTrendingPodcastsJob = viewModelScope.launch {
            val params = GetTrendingPodcastsPayload(
                inCategories = TrendingCategory.categories,
                max = MAX_TRENDING_PODCASTS,
            )
            getTrendingPodcastsUseCase(params)
                .onSuccess { podcasts ->
                    _viewState.update { ViewState.PodcastList(podcasts) }
                }
                .onFailure { /* TODO */ }
        }
    }

    sealed interface ViewState {
        object InitialLoading : ViewState
        data class PodcastList(val podcasts: List<TrendingPodcast>) : ViewState
        object Error : ViewState
    }

    companion object {
        private const val MAX_TRENDING_PODCASTS = 30
    }

}
