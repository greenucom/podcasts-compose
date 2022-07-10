package com.greencom.android.podcasts2.ui.route.discover

import com.greencom.android.podcasts2.domain.category.usecase.GetSelectableTrendingCategoriesUseCase
import com.greencom.android.podcasts2.domain.category.usecase.ToggleSelectableTrendingCategoryUseCase
import com.greencom.android.podcasts2.domain.podcast.usecase.GetTrendingPodcastsForSelectedTrendingCategoriesUseCase
import com.greencom.android.podcasts2.domain.podcast.usecase.SavePodcastUseCase
import com.greencom.android.podcasts2.domain.podcast.usecase.UpdateSubscriptionToPodcastUseCase
import javax.inject.Inject

class DiscoverInteractor @Inject constructor(
    val getSelectableTrendingCategories: GetSelectableTrendingCategoriesUseCase,
    val toggleSelectableTrendingCategory: ToggleSelectableTrendingCategoryUseCase,
    val getTrendingPodcastsForSelectedTrendingCategories: GetTrendingPodcastsForSelectedTrendingCategoriesUseCase,
    val savePodcast: SavePodcastUseCase,
    val updateSubscriptionToPodcast: UpdateSubscriptionToPodcastUseCase,
)
