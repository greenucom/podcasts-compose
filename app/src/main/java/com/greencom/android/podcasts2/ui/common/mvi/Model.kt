package com.greencom.android.podcasts2.ui.common.mvi

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface Model<ViewState : State, ViewEvent : Event, ViewSideEffect : SideEffect> {
    val state: StateFlow<ViewState>
    val sideEffects: Flow<ViewSideEffect>
    fun dispatchEvent(event: ViewEvent)
}
