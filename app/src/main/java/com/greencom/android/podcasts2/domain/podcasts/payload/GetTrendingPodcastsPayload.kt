package com.greencom.android.podcasts2.domain.podcasts.payload

import com.greencom.android.podcasts2.domain.categories.ICategory

data class GetTrendingPodcastsPayload(
    val inCategories: List<ICategory>,
    val max: Int = MAX_DEFAULT,
    val language: String = RU,
    val notInCategories: List<ICategory> = emptyList(),
) {

    constructor(
        inCategory: ICategory,
        max: Int = MAX_DEFAULT,
        language: String = RU,
        notInCategories: List<ICategory> = emptyList(),
    ) : this(
        inCategories = listOf(inCategory),
        max = max,
        language = language,
        notInCategories = notInCategories,
    )

    companion object {
        private const val MAX_DEFAULT = 20
        private const val RU = "ru"
    }

}
