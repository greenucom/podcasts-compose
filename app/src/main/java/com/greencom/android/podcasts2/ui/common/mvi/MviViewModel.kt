package com.greencom.android.podcasts2.ui.common.mvi

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update

abstract class MviViewModel<ViewState : State, UserIntent : Intent, ViewSideEffect : SideEffect> :
    ViewModel(), Model<ViewState, UserIntent, ViewSideEffect> {

    protected abstract val initialViewState: ViewState

    protected val _state by lazy { MutableStateFlow(initialViewState) }
    override val state by lazy { _state }

    private val _sideEffects = Channel<ViewSideEffect>(Channel.UNLIMITED)
    override val sideEffects = _sideEffects.receiveAsFlow()

    protected inline fun updateState(function: (ViewState) -> ViewState) {
        _state.update(function)
    }

    protected fun emitSideEffect(sideEffect: ViewSideEffect) {
        _sideEffects.trySend(sideEffect)
    }

}
