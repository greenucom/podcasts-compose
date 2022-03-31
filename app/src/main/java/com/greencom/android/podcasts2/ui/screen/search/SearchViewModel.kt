package com.greencom.android.podcasts2.ui.screen.search

import com.greencom.android.podcasts2.ui.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(

) : BaseViewModel<SearchViewModel.ViewState, SearchViewModel.ViewEvent>() {

    override val initialViewState = ViewState.None

    sealed interface ViewState {
        object None : ViewState
    }

    sealed interface ViewEvent

}
