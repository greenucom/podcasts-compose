package com.greencom.android.podcasts2.ui.screen.app

import com.greencom.android.podcasts2.ui.common.BaseViewModel
import com.greencom.android.podcasts2.ui.navigation.BottomNavBarItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(

) : BaseViewModel<AppViewModel.ViewState, AppViewModel.ViewEvent>() {

    override val initialViewState = ViewState.None

    fun onBottomNavBarItemReselected(item: BottomNavBarItem) {
        if (item is BottomNavBarItem.Discover) {
            sendEvent(ViewEvent.DiscoverBottomNavBarItemReselected)
        }
    }

    sealed interface ViewState {
        object None : ViewState
    }

    sealed interface ViewEvent {
        object DiscoverBottomNavBarItemReselected : ViewEvent
    }

}
