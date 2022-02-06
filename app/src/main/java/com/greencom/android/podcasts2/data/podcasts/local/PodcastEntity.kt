package com.greencom.android.podcasts2.data.podcasts.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.greencom.android.podcasts2.domain.podcasts.IPodcast
import com.greencom.android.podcasts2.domain.podcasts.TrendingPodcast

@Entity(tableName = "podcasts")
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

    @ColumnInfo(name = "is_subscribed", defaultValue = "0")
    val isSubscribed: Boolean,

) {

    fun toTrendingPodcast(): TrendingPodcast = TrendingPodcast(
        id = id,
        title = title,
        description = description,
        author = author,
        image = image,
        isSubscribed = isSubscribed,
    )

    companion object {

        fun fromDomain(podcast: IPodcast): PodcastEntity = PodcastEntity(
            id = podcast.id,
            title = podcast.title,
            description = podcast.description,
            author = podcast.author,
            image = podcast.image,
            isSubscribed = podcast.isSubscribed,
        )

    }

}
