package com.greencom.android.podcasts2.ui.model.podcast

import androidx.compose.runtime.Immutable
import com.greencom.android.podcasts2.ui.model.category.CategoryUiModel

@Immutable
data class PodcastUiModel(
    val id: Long,
    val title: String,
    val description: String,
    val author: String,
    val imageUrl: String,
    val categories: List<CategoryUiModel>,
    val isUserSubscribed: Boolean,
)
