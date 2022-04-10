package com.greencom.android.podcasts2.data.podcast

import com.greencom.android.podcasts2.data.podcast.remote.PodcastService
import com.greencom.android.podcasts2.domain.category.Category
import com.greencom.android.podcasts2.domain.category.toCategoriesString
import com.greencom.android.podcasts2.domain.language.Language
import com.greencom.android.podcasts2.domain.language.toLanguagesString
import com.greencom.android.podcasts2.domain.podcast.Podcast
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PodcastRemoteDataSource @Inject constructor(
    private val podcastService: PodcastService,
) {

    private val _trendingPodcasts = MutableSharedFlow<List<Podcast>>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST,
    )
    val trendingPodcasts = _trendingPodcasts.asSharedFlow()

    private val _lastSearchPodcastsResult = MutableSharedFlow<List<Podcast>>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST,
    )
    val lastSearchPodcastsResult = _lastSearchPodcastsResult.asSharedFlow()

    suspend fun loadTrendingPodcasts(
        max: Int,
        languages: List<Language>,
        inCategories: List<Category>,
        notInCategories: List<Category>,
    ) {
        val dto = podcastService.getTrendingPodcasts(
            max = max,
            languages = languages.toLanguagesString(),
            inCategories = inCategories.toCategoriesString(),
            notInCategories = notInCategories.toCategoriesString(),
        )
        val podcasts = dto.toDomain()
        _trendingPodcasts.tryEmit(podcasts)
    }

    suspend fun searchPodcasts(query: String) {
        val dto = podcastService.searchPodcasts(query)
        val podcasts = dto.toDomain()
        _lastSearchPodcastsResult.tryEmit(podcasts)
    }

}
