package com.greencom.android.podcasts2.ui.screen.podcast

import androidx.lifecycle.viewModelScope
import com.greencom.android.podcasts2.domain.episode.Episode
import com.greencom.android.podcasts2.domain.podcast.Podcast
import com.greencom.android.podcasts2.domain.podcast.usecase.GetPodcastWithEpisodesFlowUseCase
import com.greencom.android.podcasts2.ui.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PodcastViewModel @Inject constructor(
    private val interactor: PodcastInteractor,
) : BaseViewModel<PodcastViewModel.ViewState, PodcastViewModel.ViewEvent>() {

    override val initialViewState = ViewState.InitialLoading

    private var podcastId = 0L

    fun setParameters(podcastId: Long) {
        this.podcastId = podcastId
        loadPodcastWithEpisodes()
    }

    private fun loadPodcastWithEpisodes() = viewModelScope.launch {
        val params = GetPodcastWithEpisodesFlowUseCase.Params(podcastId)
        interactor.getPodcastWithEpisodesFlowUseCase(params).collect { result ->
            result.onSuccess(::onLoadPodcastWithEpisodesSuccess)
        }
    }

    private fun onLoadPodcastWithEpisodesSuccess(podcastWithEpisodes: Pair<Podcast?, List<Episode>>) {
        val (podcast, episodes) = podcastWithEpisodes
        val viewState = if (podcast != null) {
            ViewState.Success(podcast, episodes)
        } else {
            ViewState.InitialLoading
        }
        updateViewState { viewState }
    }

    sealed interface ViewState {
        object InitialLoading : ViewState
        data class Success(
            val podcast: Podcast,
            val episodes: List<Episode>,
        ) : ViewState
    }

    sealed interface ViewEvent

}
