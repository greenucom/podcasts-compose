package com.greencom.android.podcasts2.ui.common.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber

abstract class MviViewModel<ViewState : State, ViewEvent : Event, ViewSideEffect : SideEffect> :
    ViewModel(), Model<ViewState, ViewEvent, ViewSideEffect> {

    protected abstract val initialViewState: ViewState

    protected val _state by lazy { MutableStateFlow(initialViewState) }
    override val state by lazy { _state }

    private val _events = Channel<ViewEvent>(Channel.UNLIMITED)
    private val events = _events.consumeAsFlow()

    private val _sideEffects = Channel<ViewSideEffect>(Channel.UNLIMITED)
    override val sideEffects = _sideEffects.receiveAsFlow()

    override fun dispatchEvent(event: ViewEvent) {
        _events.trySend(event)
    }

    init {
        Timber.d("${this.javaClass.simpleName} init")
        consumeEvents()
    }

    override fun onCleared() {
        Timber.d("${this.javaClass.simpleName} cleared")
        super.onCleared()
    }

    private fun consumeEvents() = viewModelScope.launch {
        events.collect(::handleEvent)
    }

    protected abstract suspend fun handleEvent(event: ViewEvent)

    protected inline fun updateState(function: (ViewState) -> ViewState) {
        _state.update(function)
    }

    protected fun emitSideEffect(sideEffect: ViewSideEffect) {
        _sideEffects.trySend(sideEffect)
    }

}
