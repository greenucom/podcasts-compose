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
        val event = when (item) {
            BottomNavBarItem.MyPodcasts -> ViewEvent.MyPodcastsBottomNavBarItemReselected
            BottomNavBarItem.Discover -> ViewEvent.DiscoverBottomNavBarItemReselected
            BottomNavBarItem.Library -> ViewEvent.LibraryBottomNavBarItemReselected
            BottomNavBarItem.Profile -> ViewEvent.ProfileBottomNavBarItemReselected
        }
        sendEvent(event)
    }

    sealed interface ViewState {
        object None : ViewState
    }

    sealed interface ViewEvent {
        object MyPodcastsBottomNavBarItemReselected : ViewEvent
        object DiscoverBottomNavBarItemReselected : ViewEvent
        object LibraryBottomNavBarItemReselected : ViewEvent
        object ProfileBottomNavBarItemReselected : ViewEvent
    }

}
