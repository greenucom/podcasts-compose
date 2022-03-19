package com.greencom.android.podcasts2.domain.podcast.usecase

import com.greencom.android.podcasts2.base.clean.UseCase
import com.greencom.android.podcasts2.data.podcast.PodcastRepository
import com.greencom.android.podcasts2.di.IODispatcher
import com.greencom.android.podcasts2.domain.category.Category
import com.greencom.android.podcasts2.domain.language.Language
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class RequestTrendingPodcastsUseCase @Inject constructor(
    @IODispatcher dispatcher: CoroutineDispatcher,
    private val podcastRepository: PodcastRepository,
) : UseCase<GetTrendingPodcastsPayload, Unit>(dispatcher) {

    override suspend fun execute(params: GetTrendingPodcastsPayload) {
        val (max, languages, inCategories, notInCategories) = params
        podcastRepository.loadTrendingPodcasts(
            max = max,
            languages = languages,
            inCategories = inCategories,
            notInCategories = notInCategories,
        )
    }

}

data class GetTrendingPodcastsPayload(
    val max: Int = MAX_DEFAULT_VALUE,
    val languages: List<Language> = listOf(Language.Ru),
    val inCategories: List<Category> = emptyList(),
    val notInCategories: List<Category> = emptyList(),
) {

    companion object {
        private const val MAX_DEFAULT_VALUE = 20
    }

}
