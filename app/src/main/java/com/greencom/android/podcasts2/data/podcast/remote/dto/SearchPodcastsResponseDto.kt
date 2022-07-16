package com.greencom.android.podcasts2.data.podcast.remote.dto

import android.net.Uri
import com.greencom.android.podcasts2.data.category.remote.dto.CategoriesDto
import com.greencom.android.podcasts2.data.category.remote.dto.toCategories
import com.greencom.android.podcasts2.domain.podcast.Podcast
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchPodcastsResponseDto(
    @SerialName("feeds") val podcasts: List<SearchPodcastsItemDto>? = null,
    @SerialName("count") val count: Int? = null,
) {

    fun toPodcasts(): List<Podcast> {
        return podcasts?.map { it.toPodcast() } ?: emptyList()
    }

}

@Serializable
data class SearchPodcastsItemDto(
    @SerialName("id") val id: Long? = null,
    @SerialName("url") val url: String? = null,
    @SerialName("title") val title: String? = null,
    @SerialName("description") val description: String? = null,
    @SerialName("author") val author: String? = null,
    @SerialName("image") val image: String? = null,
    @SerialName("language") val language: String? = null,
    @SerialName("categories") val categories: CategoriesDto? = null,
) {

    fun toPodcast(): Podcast = Podcast(
        id = checkNotNull(id),
        title = checkNotNull(title),
        description = checkNotNull(description),
        author = checkNotNull(author),
        imageUrl = Uri.decode(checkNotNull(image)),
        categories = categories?.toCategories() ?: emptyList(),
        isUserSubscribed = false,
    )

}
