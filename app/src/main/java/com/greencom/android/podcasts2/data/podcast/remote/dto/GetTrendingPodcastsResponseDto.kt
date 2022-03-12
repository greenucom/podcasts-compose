package com.greencom.android.podcasts2.data.podcast.remote.dto

import com.greencom.android.podcasts2.data.category.remote.dto.CategoriesDto
import com.greencom.android.podcasts2.data.category.remote.dto.toDomain
import com.greencom.android.podcasts2.domain.category.usecase.GetCategoryDisplayNameUseCase
import com.greencom.android.podcasts2.domain.podcast.TrendingPodcast
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetTrendingPodcastsResponseDto(

    @SerialName("status")
    val status: String? = null,

    @SerialName("feeds")
    val podcasts: List<GetTrendingPodcastResponseDto>? = null,

    @SerialName("count")
    val count: Int? = null,

    @SerialName("max")
    val max: Int? = null,

) {

    fun toDomain(
        getCategoryDisplayNameUseCase: GetCategoryDisplayNameUseCase,
    ): List<TrendingPodcast> {
        return podcasts?.map { it.toDomain(getCategoryDisplayNameUseCase) } ?: emptyList()
    }

}

@Serializable
data class GetTrendingPodcastResponseDto(
    
    @SerialName("id")
    val id: Long? = null,
    
    @SerialName("url")
    val url: String? = null,
    
    @SerialName("title")
    val title: String? = null,
    
    @SerialName("description")
    val description: String? = null,
    
    @SerialName("author")
    val author: String? = null,
    
    @SerialName("image")
    val image: String? = null,
    
    @SerialName("newestItemPublishedTime")
    val newestItemPublishedTime: Long? = null,
    
    @SerialName("trendScore")
    val trendScore: Int? = null,

    @SerialName("language")
    val language: String? = null,

    @SerialName("categories")
    val categories: CategoriesDto? = null,
    
) {

    fun toDomain(
        getCategoryDisplayNameUseCase: GetCategoryDisplayNameUseCase,
    ): TrendingPodcast = TrendingPodcast(
        id = checkNotNull(id),
        title = checkNotNull(title),
        description = checkNotNull(description),
        author = checkNotNull(author),
        image = checkNotNull(image),
        categories = categories?.toDomain(getCategoryDisplayNameUseCase) ?: emptyList(),
        isSubscribed = false,
    )

}
