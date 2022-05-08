package com.greencom.android.podcasts2.ui.common.mvi

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface Model<ViewState : State, UserIntent : Intent, ViewSideEffect : SideEffect> {
    val state: StateFlow<ViewState>
    val sideEffects: Flow<ViewSideEffect>
    fun dispatchIntent(intent: UserIntent)
}
