package com.greencom.android.podcasts2.data.podcast.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.greencom.android.podcasts2.data.category.local.CategoryEntityDto
import com.greencom.android.podcasts2.data.category.local.CategoryEntityDtoListTypeConverter

@Entity(tableName = "podcasts_temp")
@TypeConverters(CategoryEntityDtoListTypeConverter::class)
data class PodcastEntityTemp(

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "author")
    val author: String,

    @ColumnInfo(name = "image_url")
    val imageUrl: String,

    @ColumnInfo(name = "categories")
    val categories: List<CategoryEntityDto>,

    @ColumnInfo(name = "is_subscribed", defaultValue = "0")
    val isSubscribed: Boolean,

)
