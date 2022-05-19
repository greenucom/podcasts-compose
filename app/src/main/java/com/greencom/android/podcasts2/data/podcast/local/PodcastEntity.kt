package com.greencom.android.podcasts2.data.podcast.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.greencom.android.podcasts2.data.category.local.CategoryDtoListTypeConverter
import com.greencom.android.podcasts2.data.category.local.dto.CategoryDto

@Entity(tableName = "Podcasts")
@TypeConverters(CategoryDtoListTypeConverter::class)
data class PodcastEntity(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "author") val author: String,
    @ColumnInfo(name = "image_url") val imageUrl: String,
    @ColumnInfo(name = "categories") val categories: List<CategoryDto>,
    @ColumnInfo(name = "is_user_subscribed") val isUserSubscribed: Boolean,
)
