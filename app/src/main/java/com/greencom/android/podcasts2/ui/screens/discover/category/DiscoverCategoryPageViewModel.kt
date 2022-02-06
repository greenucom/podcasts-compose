package com.greencom.android.podcasts2.ui.screens.discover.category

import androidx.lifecycle.viewModelScope
import com.greencom.android.podcasts2.domain.categories.TrendingCategory
import com.greencom.android.podcasts2.domain.podcasts.IPodcast
import com.greencom.android.podcasts2.domain.podcasts.TrendingPodcast
import com.greencom.android.podcasts2.domain.podcasts.payload.GetTrendingPodcastsPayload
import com.greencom.android.podcasts2.domain.podcasts.usecases.ChangeSubscriptionUseCase
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
class DiscoverCategoryPageViewModel @Inject constructor(
    private val getTrendingPodcastsUseCase: GetTrendingPodcastsUseCase,
    private val changeSubscriptionUseCase: ChangeSubscriptionUseCase,
) : BaseViewModel() {

    private val _viewState = MutableStateFlow<ViewState>(ViewState.InitialLoading)
    val viewState = _viewState.asStateFlow()

    private var loadTrendingPodcastsJob: Job? = null

    fun loadTrendingPodcasts(category: TrendingCategory) {
        loadTrendingPodcastsJob?.cancel()
        loadTrendingPodcastsJob = viewModelScope.launch {
            val params = GetTrendingPodcastsPayload(category)
            getTrendingPodcastsUseCase(params).fold(
                onSuccess = ::onLoadTrendingPodcastsSuccess,
                onFailure = { _viewState.update { ViewState.Error } },
            )
        }
    }

    private fun onLoadTrendingPodcastsSuccess(podcasts: List<TrendingPodcast>) {
        _viewState.update { ViewState.TrendingPodcasts(podcasts) }
    }

    fun onSubscriptionChanged(podcast: IPodcast) = viewModelScope.launch {
        changeSubscriptionUseCase(podcast)
    }

    sealed interface ViewState {
        object InitialLoading : ViewState
        data class TrendingPodcasts(val podcasts: List<TrendingPodcast>) : ViewState
        object Error : ViewState
    }

}
