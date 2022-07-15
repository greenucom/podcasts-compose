package com.greencom.android.podcasts2.ui.route.search

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.greencom.android.podcasts2.ui.common.mvi.Event
import com.greencom.android.podcasts2.ui.common.mvi.MviViewModel
import com.greencom.android.podcasts2.ui.common.mvi.SideEffect
import com.greencom.android.podcasts2.ui.common.mvi.State
import com.greencom.android.podcasts2.utils.emptyString
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(

) : MviViewModel<SearchViewModel.ViewState, SearchViewModel.ViewEvent, SearchViewModel.ViewSideEffect>() {

    override val initialViewState = ViewState()

    override suspend fun handleEvent(event: ViewEvent) = when (event) {
        is ViewEvent.TextFieldValueChanged -> reduceTextFieldValueChanged(event)
        ViewEvent.ClearTextField -> reduceClearTextField()
    }

    private fun reduceTextFieldValueChanged(event: ViewEvent.TextFieldValueChanged) {
        updateState { it.copy(textFieldValue = event.textFieldValue) }
    }

    private fun reduceClearTextField() {
        updateState { it.copy(textFieldValue = emptyString()) }
    }

    @Immutable
    data class ViewState(
        val textFieldValue: String = emptyString(),
    ) : State

    @Stable
    sealed interface ViewEvent : Event {
        data class TextFieldValueChanged(val textFieldValue: String) : ViewEvent
        object ClearTextField : ViewEvent
    }

    @Stable
    sealed interface ViewSideEffect : SideEffect

}
