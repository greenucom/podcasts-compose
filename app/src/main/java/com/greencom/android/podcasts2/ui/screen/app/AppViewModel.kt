package com.greencom.android.podcasts2.ui.screen.app

import androidx.lifecycle.viewModelScope
import com.greencom.android.podcasts2.ui.common.BaseViewModel
import com.greencom.android.podcasts2.ui.navigation.BottomNavBarItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor() : BaseViewModel() {

//    private val _appState = MutableStateFlow(AppState())
//    val appState = _appState.asStateFlow()
//
//    fun onBottomNavBarItemReselected(item: BottomNavBarItem) {
//        _appState.update { it.copy(reselectedBottomNavBarItem = item) }
//    }
//
//    fun onReselectedBottomNavBarItemHandled() {
//        _appState.update { it.copy(reselectedBottomNavBarItem = null) }
//    }
//
//    data class AppState(
//        val reselectedBottomNavBarItem: BottomNavBarItem? = null,
//    )

    private val _events = Channel<Event>(Channel.UNLIMITED)
    val events = _events.receiveAsFlow()

    private fun setEvent(event: Event) = viewModelScope.launch {
        _events.send(event)
    }

    fun onBottomNavBarItemReselected(item: BottomNavBarItem) {
        if (item is BottomNavBarItem.Discover) {
            setEvent(Event.DiscoverBottomNavBarItemReselected)
        }
    }

    sealed interface Event {
        object DiscoverBottomNavBarItemReselected : Event
    }

}
