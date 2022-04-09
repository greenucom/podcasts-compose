package com.greencom.android.podcasts2.data.podcast

import com.greencom.android.podcasts2.data.podcast.remote.PodcastService
import com.greencom.android.podcasts2.domain.category.Category
import com.greencom.android.podcasts2.domain.category.toCategoriesString
import com.greencom.android.podcasts2.domain.language.Language
import com.greencom.android.podcasts2.domain.language.toLanguagesString
import com.greencom.android.podcasts2.domain.podcast.Podcast
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PodcastRemoteDataSource @Inject constructor(
    private val podcastService: PodcastService,
) {

    private val _trendingPodcasts = MutableStateFlow<List<Podcast>>(emptyList())
    val trendingPodcasts = _trendingPodcasts.asStateFlow()

    private val _lastSearchPodcastsResult = MutableStateFlow<List<Podcast>>(emptyList())
    val lastSearchPodcastsResult = _lastSearchPodcastsResult.asStateFlow()

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
        _trendingPodcasts.update { podcasts }
    }

    suspend fun searchPodcasts(query: String) {
        val dto = podcastService.searchPodcasts(query)
        val podcasts = dto.toDomain()
        _lastSearchPodcastsResult.update { podcasts }
    }

}
