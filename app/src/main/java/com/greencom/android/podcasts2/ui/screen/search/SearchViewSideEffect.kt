package com.greencom.android.podcasts2.ui.screen.search

import com.greencom.android.podcasts2.ui.common.mvi.SideEffect

sealed interface SearchViewSideEffect : SideEffect {
    object RequestSearchFieldFocus : SearchViewSideEffect
    object ClearSearchFieldFocus : SearchViewSideEffect
}
