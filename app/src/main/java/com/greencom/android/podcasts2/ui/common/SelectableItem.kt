package com.greencom.android.podcasts2.ui.common

import androidx.compose.runtime.Immutable

@Immutable
data class SelectableItem<T>(
    val item: T,
    val isSelected: Boolean,
)
