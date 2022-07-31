package com.greencom.android.podcasts2.ui.common.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.greencom.android.podcasts2.utils.relaunchIn
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import timber.log.Timber

abstract class MviViewModel<ViewState : State, ViewEvent : Event, ViewSideEffect : SideEffect> :
    ViewModel(), Model<ViewState, ViewEvent, ViewSideEffect> {

    protected abstract val initialViewState: ViewState

    protected val _state by lazy { MutableStateFlow(initialViewState) }
    override val state by lazy { _state }

    private val _events = Channel<ViewEvent>(Channel.UNLIMITED)
    private val events = _events.receiveAsFlow()

    private val _sideEffects = Channel<ViewSideEffect>(Channel.UNLIMITED)
    override val sideEffects = _sideEffects.receiveAsFlow()

    private val consumeEventsJob = MutableStateFlow<Job?>(null)

    init {
        Timber.d("${this.javaClass.simpleName} init")

        consumeEventsJob.relaunchIn(viewModelScope) {
            consumeEvents(events)
        }
    }

    protected abstract suspend fun consumeEvents(events: Flow<ViewEvent>)

    override fun dispatchEvent(event: ViewEvent) {
        _events.trySend(event)
    }

    protected inline fun updateState(function: (state: ViewState) -> ViewState) {
        _state.update(function)
    }

    protected fun emitSideEffect(sideEffect: ViewSideEffect) {
        _sideEffects.trySend(sideEffect)
    }

    override fun onCleared() {
        super.onCleared()
        Timber.d("${this.javaClass.simpleName} cleared")
    }

}
