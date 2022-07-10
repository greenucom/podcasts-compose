package com.greencom.android.podcasts2.ui.route.dialog.podcastdescription

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.greencom.android.podcasts2.domain.podcast.Podcast
import com.greencom.android.podcasts2.domain.podcast.usecase.GetPodcastUseCase
import com.greencom.android.podcasts2.ui.common.mvi.Event
import com.greencom.android.podcasts2.ui.common.mvi.MviViewModel
import com.greencom.android.podcasts2.ui.common.mvi.SideEffect
import com.greencom.android.podcasts2.ui.common.mvi.State
import com.greencom.android.podcasts2.ui.navigation.DialogRoute
import com.greencom.android.podcasts2.utils.emptyString
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.launch
import javax.inject.Inject

class PodcastDescriptionViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val interactor: PodcastDescriptionInteractor,
) : MviViewModel<
        PodcastDescriptionViewModel.ViewState,
        PodcastDescriptionViewModel.ViewEvent,
        PodcastDescriptionViewModel.ViewSideEffect>() {

    override val initialViewState = ViewState(
        imageUrl = emptyString(),
        title = emptyString(),
        description = emptyString(),
    )

    private val podcastId: StateFlow<Long>

    private val collectPodcastJob = MutableStateFlow<Job?>(null)

    init {
        podcastId = savedStateHandle.getStateFlow(
            key = DialogRoute.PodcastDescription.ArgPodcastId,
            initialValue = 0L,
        )

        collectPodcast()
    }

    override suspend fun handleEvent(event: ViewEvent) = Unit

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun collectPodcast() {
        collectPodcastJob.getAndUpdate {
            viewModelScope.launch {
                podcastId.flatMapLatest { podcastId ->
                    val params = GetPodcastUseCase.Params(podcastId)
                    interactor.getPodcast(params)
                }.collect { result ->
                    result
                        .onSuccess(::updateStateWithPodcast)
                        .onFailure { emitSideEffect(ViewSideEffect.DismissDialog) }
                }
            }
        }?.cancel()
    }

    private fun updateStateWithPodcast(podcast: Podcast) {
        val state = ViewState(
            imageUrl = podcast.imageUrl,
            title = podcast.title,
            description = podcast.description,
        )
        updateState { state }
    }

    data class ViewState(
        val imageUrl: String,
        val title: String,
        val description: String,
    ) : State

    sealed interface ViewEvent : Event

    sealed interface ViewSideEffect : SideEffect {
        object DismissDialog : ViewSideEffect
    }

}
