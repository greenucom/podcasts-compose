package com.greencom.android.podcasts2.ui.route.podcast

import androidx.compose.runtime.Stable
import androidx.lifecycle.SavedStateHandle
import com.greencom.android.podcasts2.ui.common.mvi.Event
import com.greencom.android.podcasts2.ui.common.mvi.MviViewModel
import com.greencom.android.podcasts2.ui.common.mvi.SideEffect
import com.greencom.android.podcasts2.ui.common.mvi.State
import com.greencom.android.podcasts2.ui.navigation.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class PodcastViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : MviViewModel<PodcastViewModel.ViewState, PodcastViewModel.ViewEvent, PodcastViewModel.ViewSideEffect>() {

    override val initialViewState = ViewState()

    private val podcastId: StateFlow<Long>

    init {
        podcastId = savedStateHandle.getStateFlow(key = Route.Podcast.KeyPodcastId, initialValue = 0L)
    }

    override suspend fun consumeEvents(events: Flow<ViewEvent>) {
        // TODO("Not yet implemented")
    }

    // TODO: Implement, add annotations
    class ViewState : State

    @Stable
    sealed interface ViewEvent : Event

    @Stable
    sealed interface ViewSideEffect : SideEffect

}
