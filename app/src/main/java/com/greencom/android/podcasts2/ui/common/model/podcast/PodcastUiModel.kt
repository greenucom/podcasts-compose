package com.greencom.android.podcasts2.ui.common.model.podcast

import androidx.compose.runtime.Immutable
import com.greencom.android.podcasts2.domain.podcast.Podcast
import com.greencom.android.podcasts2.ui.common.model.category.CategoryUiModel

@Immutable
data class PodcastUiModel(
    val id: Long,
    val title: String,
    val description: String,
    val author: String,
    val imageUrl: String,
    val categories: List<CategoryUiModel>,
    val isSubscribed: Boolean
) {

    fun toPodcast(): Podcast = Podcast(
        id = id,
        title = title,
        description = description,
        author = author,
        imageUrl = imageUrl,
        categories = categories.map { it.toCategory() },
        isSubscribed = isSubscribed,
    )

    companion object {

        fun fromPodcast(podcast: Podcast): PodcastUiModel = PodcastUiModel(
            id = podcast.id,
            title = podcast.title,
            description = podcast.description,
            author = podcast.author,
            imageUrl = podcast.imageUrl,
            categories = podcast.categories.map { CategoryUiModel.fromCategory(it) },
            isSubscribed = podcast.isSubscribed,
        )

    }

}
