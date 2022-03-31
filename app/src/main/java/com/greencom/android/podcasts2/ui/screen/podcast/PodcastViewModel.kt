package com.greencom.android.podcasts2.ui.screen.podcast

import com.greencom.android.podcasts2.ui.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PodcastViewModel @Inject constructor(

) : BaseViewModel<PodcastViewModel.ViewState, PodcastViewModel.ViewEvent>() {

    override val initialViewState = ViewState.InitialLoading

    private var podcastId = 0L

    fun setParameters(podcastId: Long) {
        this.podcastId = podcastId
    }

    sealed interface ViewState {
        object InitialLoading : ViewState
    }

    sealed interface ViewEvent

}
