package com.greencom.android.podcasts2.ui.common

import androidx.compose.foundation.lazy.LazyListState

/**
 * Perform an instant scroll to [instantScrollIndex] and than animate scroll [smoothScrollIndex].
 */
suspend fun LazyListState.fastScrollTo(
    instantScrollIndex: Int,
    smoothScrollIndex: Int,
) {
    require(instantScrollIndex >= 0) { "instantScrollIndex should be non-negative ($instantScrollIndex)" }
    require(smoothScrollIndex >= 0) { "smoothScrollIndex should be non-negative ($smoothScrollIndex)" }

    this.scrollToItem(instantScrollIndex)
    this.animateScrollToItem(smoothScrollIndex)
}
