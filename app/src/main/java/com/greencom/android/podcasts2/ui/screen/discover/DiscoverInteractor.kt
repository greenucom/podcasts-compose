package com.greencom.android.podcasts2.ui.screen.discover

import com.greencom.android.podcasts2.domain.category.usecase.GetSelectedTrendingCategoriesIdsUseCase
import com.greencom.android.podcasts2.domain.category.usecase.GetTrendingCategoriesUseCase
import com.greencom.android.podcasts2.domain.category.usecase.ToggleSelectableTrendingCategoryUseCase
import com.greencom.android.podcasts2.domain.podcast.usecase.RequestTrendingPodcastsUseCase
import com.greencom.android.podcasts2.domain.podcast.usecase.GetTrendingPodcastsFlowUseCase
import com.greencom.android.podcasts2.domain.podcast.usecase.UpdatePodcastSubscriptionUseCase
import javax.inject.Inject

class DiscoverInteractor @Inject constructor(
    val getTrendingCategoriesUseCase: GetTrendingCategoriesUseCase,
    val getSelectedTrendingCategoriesIdsUseCase: GetSelectedTrendingCategoriesIdsUseCase,
    val toggleSelectableTrendingCategoryUseCase: ToggleSelectableTrendingCategoryUseCase,
    val requestTrendingPodcastsUseCase: RequestTrendingPodcastsUseCase,
    val getTrendingPodcastsFlowUseCase: GetTrendingPodcastsFlowUseCase,
    val updatePodcastSubscriptionUseCase: UpdatePodcastSubscriptionUseCase,
)
