package com.greencom.android.podcasts2.ui.screen.discover

import com.greencom.android.podcasts2.ui.common.mvi.MviViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DiscoverViewModel @Inject constructor(
    private val interactor: DiscoverInteractor,
) : MviViewModel<DiscoverViewState, DiscoverViewEvent, DiscoverViewSideEffect>() {

    override val initialViewState = DiscoverViewState.InitialLoading

    override suspend fun handleEvent(event: DiscoverViewEvent) {
        TODO("Not yet implemented")
    }

}
