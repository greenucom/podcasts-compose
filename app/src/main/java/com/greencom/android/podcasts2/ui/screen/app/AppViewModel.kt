package com.greencom.android.podcasts2.ui.screen.app

import com.greencom.android.podcasts2.ui.common.BaseViewModel
import com.greencom.android.podcasts2.ui.navigation.BottomNavBarItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor() : BaseViewModel() {

    private val _appState = MutableStateFlow(AppState())
    val appState = _appState.asStateFlow()

    fun onBottomNavBarItemReselected(item: BottomNavBarItem) {
        _appState.update { it.copy(reselectedBottomNavBarItem = item) }
    }

    fun onReselectedBottomNavBarItemHandled() {
        _appState.update { it.copy(reselectedBottomNavBarItem = null) }
    }

    data class AppState(
        val reselectedBottomNavBarItem: BottomNavBarItem? = null,
    )

}
