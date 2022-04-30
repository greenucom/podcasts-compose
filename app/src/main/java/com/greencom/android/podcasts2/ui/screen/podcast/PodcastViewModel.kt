package com.greencom.android.podcasts2.ui.screen.podcast

import androidx.lifecycle.viewModelScope
import com.greencom.android.podcasts2.domain.episode.Episode
import com.greencom.android.podcasts2.domain.podcast.Podcast
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

    }

    private fun onLoadPodcastWithEpisodesSuccess(podcastWithEpisodes: Map<Podcast, List<Episode>>) {
        val podcast = podcastWithEpisodes.keys.first()
        val episodes = podcastWithEpisodes[podcast] ?: emptyList()
        val viewState = ViewState.Success(podcast, episodes)
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
