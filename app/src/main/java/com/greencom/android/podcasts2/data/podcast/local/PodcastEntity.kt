package com.greencom.android.podcasts2.data.podcast.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.greencom.android.podcasts2.data.category.local.CategoryEntityDto
import com.greencom.android.podcasts2.data.category.local.CategoryEntityDtoListTypeConverter
import com.greencom.android.podcasts2.domain.podcast.Podcast

@Entity(tableName = "Podcast")
@TypeConverters(CategoryEntityDtoListTypeConverter::class)
data class PodcastEntity(

    @PrimaryKey
    @ColumnInfo(name = "podcast_id")
    val id: Long,

    @ColumnInfo(name = "podcast_title")
    val title: String,

    @ColumnInfo(name = "podcast_description")
    val description: String,

    @ColumnInfo(name = "podcast_author")
    val author: String,

    @ColumnInfo(name = "podcast_image_url")
    val imageUrl: String,

    @ColumnInfo(name = "podcast_categories")
    val categories: List<CategoryEntityDto>,

    @ColumnInfo(name = "podcast_is_subscribed", defaultValue = "0")
    val isSubscribed: Boolean,

) {

    fun toPodcast() = Podcast(
        id = id,
        title = title,
        description = description,
        author = author,
        imageUrl = imageUrl,
        categories = categories.map { it.toCategory() },
        isSubscribed = isSubscribed,
    )

    companion object {

        fun fromPodcast(podcast: Podcast): PodcastEntity = PodcastEntity(
            id = podcast.id,
            title = podcast.title,
            description = podcast.description,
            author = podcast.author,
            imageUrl = podcast.imageUrl,
            categories = podcast.categories.map { CategoryEntityDto.fromCategory(it) },
            isSubscribed = podcast.isSubscribed,
        )

    }

}
