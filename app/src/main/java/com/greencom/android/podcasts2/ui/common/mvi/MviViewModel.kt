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

abstract class MviViewModel<ViewState : State, UserIntent : Intent, ViewSideEffect : SideEffect> :
    ViewModel(), Model<ViewState, UserIntent, ViewSideEffect> {

    protected abstract val initialViewState: ViewState

    protected val _state by lazy { MutableStateFlow(initialViewState) }
    override val state by lazy { _state }

    private val _intents = Channel<UserIntent>(Channel.UNLIMITED)
    private val intents = _intents.consumeAsFlow()

    private val _sideEffects = Channel<ViewSideEffect>(Channel.UNLIMITED)
    override val sideEffects = _sideEffects.receiveAsFlow()

    override fun dispatchIntent(intent: UserIntent) {
        _intents.trySend(intent)
    }

    init {
        Timber.d("${this.javaClass.simpleName} init")
        consumeIntents()
    }

    override fun onCleared() {
        Timber.d("${this.javaClass.simpleName} cleared")
        super.onCleared()
    }

    private fun consumeIntents() = viewModelScope.launch {
        intents.collect(::handleIntent)
    }

    protected abstract suspend fun handleIntent(intent: UserIntent)

    protected inline fun updateState(function: (ViewState) -> ViewState) {
        _state.update(function)
    }

    protected fun emitSideEffect(sideEffect: ViewSideEffect) {
        _sideEffects.trySend(sideEffect)
    }

}
