package com.greencom.android.podcasts2.ui.common

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import timber.log.Timber

abstract class BaseViewModel<State, Event> : ViewModel() {

    protected abstract val initialViewState: State

    protected val _viewState by lazy { MutableStateFlow<State>(initialViewState) }
    val viewState by lazy { _viewState.asStateFlow() }

    private val _viewEvents = Channel<Event>(Channel.UNLIMITED)
    val viewEvents = _viewEvents.receiveAsFlow()

    protected fun sendEvent(event: Event) {
        _viewEvents.trySend(event)
    }

    init {
        Timber.i("${this.javaClass.simpleName} init")
    }

    override fun onCleared() {
        super.onCleared()
        Timber.i("${this.javaClass.simpleName} cleared")
    }

}
