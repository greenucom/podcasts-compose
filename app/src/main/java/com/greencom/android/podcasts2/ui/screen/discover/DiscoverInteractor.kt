package com.greencom.android.podcasts2.ui.screen.discover

import com.greencom.android.podcasts2.domain.category.usecase.GetSelectableTrendingCategoriesUseCase
import com.greencom.android.podcasts2.domain.category.usecase.ToggleSelectableTrendingCategoryUseCase
import com.greencom.android.podcasts2.domain.podcast.usecase.GetTrendingPodcastsForSelectedTrendingCategoriesUseCase
import javax.inject.Inject

class DiscoverInteractor @Inject constructor(
    val getSelectableTrendingCategories: GetSelectableTrendingCategoriesUseCase,
    val getTrendingPodcastsForSelectedTrendingCategories: GetTrendingPodcastsForSelectedTrendingCategoriesUseCase,
    val toggleSelectableTrendingCategory: ToggleSelectableTrendingCategoryUseCase,
)
