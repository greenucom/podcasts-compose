package com.greencom.android.podcasts2.domain.podcast.usecase

import com.greencom.android.podcasts2.clean.UseCase
import com.greencom.android.podcasts2.data.podcast.PodcastRepository
import com.greencom.android.podcasts2.di.IODispatcher
import com.greencom.android.podcasts2.domain.category.ICategory
import com.greencom.android.podcasts2.domain.language.Language
import com.greencom.android.podcasts2.domain.podcast.TrendingPodcast
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetTrendingPodcastsUseCase @Inject constructor(
    @IODispatcher dispatcher: CoroutineDispatcher,
    private val podcastRepository: PodcastRepository,
) : UseCase<GetTrendingPodcastsPayload, List<TrendingPodcast>>(dispatcher) {

    override suspend fun execute(params: GetTrendingPodcastsPayload): List<TrendingPodcast> {
        val (max, languages, inCategories, notInCategories) = params
        return podcastRepository.getTrendingPodcasts(
            max = max,
            languages = languages,
            inCategories = inCategories,
            notInCategories = notInCategories,
        )
    }

}

data class GetTrendingPodcastsPayload(
    val max: Int = MAX_DEFAULT_VALUE,
    val languages: List<Language> = listOf(Language.En, Language.Ru),
    val inCategories: List<ICategory> = emptyList(),
    val notInCategories: List<ICategory> = emptyList(),
) {

    companion object {
        private const val MAX_DEFAULT_VALUE = 20
    }

}
