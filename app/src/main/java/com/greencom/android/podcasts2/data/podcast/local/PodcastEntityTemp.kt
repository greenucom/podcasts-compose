package com.greencom.android.podcasts2.data.podcast.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.greencom.android.podcasts2.data.category.local.CategoryDtoListTypeConverter
import com.greencom.android.podcasts2.data.category.local.dto.CategoryDto

@Entity(tableName = "PodcastsTemp")
@TypeConverters(CategoryDtoListTypeConverter::class)
data class PodcastEntityTemp(
    @PrimaryKey
    @ColumnInfo(name = "podcast_id") val id: Long,
    @ColumnInfo(name = "podcast_title") val title: String,
    @ColumnInfo(name = "podcast_description") val description: String,
    @ColumnInfo(name = "podcast_author") val author: String,
    @ColumnInfo(name = "podcast_image_url") val imageUrl: String,
    @ColumnInfo(name = "podcast_categories") val categories: List<CategoryDto>,
    @ColumnInfo(name = "podcast_is_user_subscribed") val isUserSubscribed: Boolean,
)
