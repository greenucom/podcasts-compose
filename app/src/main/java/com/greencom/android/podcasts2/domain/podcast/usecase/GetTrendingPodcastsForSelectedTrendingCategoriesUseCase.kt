package com.greencom.android.podcasts2.domain.podcast.usecase

import com.greencom.android.podcasts2.base.clean.FlowUseCase
import com.greencom.android.podcasts2.data.category.CategoryRepository
import com.greencom.android.podcasts2.data.podcast.PodcastRepository
import com.greencom.android.podcasts2.di.IODispatcher
import com.greencom.android.podcasts2.domain.category.Category
import com.greencom.android.podcasts2.domain.podcast.Podcast
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetTrendingPodcastsForSelectedTrendingCategoriesUseCase @Inject constructor(
    @IODispatcher dispatcher: CoroutineDispatcher,
    private val podcastRepository: PodcastRepository,
    private val categoryRepository: CategoryRepository,
) : FlowUseCase<Unit, List<Podcast>>(dispatcher) {

    override fun execute(params: Unit): Flow<Result<List<Podcast>>> {
        val trendingCategories = categoryRepository.getTrendingCategories()

        return flow {
            categoryRepository.getSelectedTrendingCategoriesIds()
                .collectLatest { idsOfSelectedCategories ->
                    val selectedCategories = trendingCategories
                        .filter { it.id in idsOfSelectedCategories }
                    val trendingPodcastsMaxSize = calculateTrendingPodcastsMaxSize(selectedCategories)
                    podcastRepository.getTrendingPodcasts(
                        max = trendingPodcastsMaxSize,
                        inCategories = selectedCategories,
                        notInCategories = emptyList(),
                    ).collect { emit(it) }
                }
        }.map { Result.success(it) }
    }

    private fun calculateTrendingPodcastsMaxSize(
        selectedCategories: List<Category>,
    ): Int {
        return if (selectedCategories.isNotEmpty()) {
            val value = selectedCategories.size * TRENDING_PODCAST_COUNT_PER_CATEGORY
            value.coerceIn(TRENDING_PODCAST_MIN_SIZE, TRENDING_PODCAST_MAX_SIZE)
        } else {
            TRENDING_PODCAST_MAX_SIZE
        }
    }

    companion object {
        private const val TRENDING_PODCAST_MIN_SIZE = 15
        private const val TRENDING_PODCAST_MAX_SIZE = 40
        private const val TRENDING_PODCAST_COUNT_PER_CATEGORY = 5
    }

}
