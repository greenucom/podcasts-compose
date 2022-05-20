package com.greencom.android.podcasts2.ui.model.category

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable

@Immutable
data class CategoryUiModel(
    val id: Int,
    val name: String,
    @StringRes val displayNameResId: Int,
)
