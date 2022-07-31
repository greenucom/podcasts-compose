package com.greencom.android.podcasts2.ui.common.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.greencom.android.podcasts2.utils.relaunchIn
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.concurrent.ConcurrentHashMap
import kotlin.time.Duration

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

    protected val debouncedEventNameToJob = ConcurrentHashMap<String, Job>()

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

    protected inline fun reduceDebouncedEvent(
        event: ViewEvent,
        timeout: Duration,
        crossinline reducer: () -> Unit,
    ) {
        val eventClass = event.javaClass
        val eventName = eventClass.canonicalName ?: eventClass.simpleName
        val currentJob = debouncedEventNameToJob[eventName]
        if (currentJob?.isCompleted != false) {
            val newJob = viewModelScope.launch {
                reducer()
                delay(timeout)
            }
            debouncedEventNameToJob[eventName] = newJob
        }
    }

    override fun onCleared() {
        super.onCleared()
        Timber.d("${this.javaClass.simpleName} cleared")
    }

}
