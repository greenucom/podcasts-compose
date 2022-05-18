package com.greencom.android.podcasts2.data.podcast.remote.dto

import com.greencom.android.podcasts2.data.category.remote.dto.CategoriesDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetTrendingPodcastsResponseDto(
    @SerialName("feeds") val feeds: List<PodcastDto>? = null,
) {

    @Serializable
    data class PodcastDto(
        @SerialName("id") val id: Long? = null,
        @SerialName("title") val title: String? = null,
        @SerialName("description") val description: String? = null,
        @SerialName("author") val author: String? = null,
        @SerialName("image") val imageUrl: String? = null,
        @SerialName("language") val language: String? = null,
        @SerialName("categories") val categories: CategoriesDto? = null,
    )

}
