package com.greencom.android.podcasts2.data.podcast.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.greencom.android.podcasts2.data.category.local.CategoryEntity
import com.greencom.android.podcasts2.data.category.local.CategoryEntityListTypeConverter
import com.greencom.android.podcasts2.domain.podcast.Podcast

@Entity(tableName = "podcasts")
@TypeConverters(CategoryEntityListTypeConverter::class)
data class PodcastEntity(

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "author")
    val author: String,

    @ColumnInfo(name = "image")
    val image: String,

    @ColumnInfo(name = "categories")
    val categories: List<CategoryEntity>,

    @ColumnInfo(name = "is_subscribed", defaultValue = "0")
    val isSubscribed: Boolean,

) {

    companion object {

        fun fromDomain(podcast: Podcast): PodcastEntity = PodcastEntity(
            id = podcast.id,
            title = podcast.title,
            description = podcast.description,
            author = podcast.author,
            image = podcast.image,
            categories = podcast.categories.map { CategoryEntity.fromDomain(it) },
            isSubscribed = podcast.isSubscribed,
        )

    }

}
