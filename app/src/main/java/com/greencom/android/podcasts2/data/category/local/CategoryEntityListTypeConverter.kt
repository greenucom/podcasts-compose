@file:Suppress("unused")

package com.greencom.android.podcasts2.data.category.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@ProvidedTypeConverter
class CategoryEntityListTypeConverter(private val json: Json) {

    @TypeConverter
    fun fromCategoryEntityList(categories: List<CategoryEntity>): String {
        return json.encodeToString(categories)
    }

    @TypeConverter
    fun toCategoryEntityList(string: String): List<CategoryEntity> {
        return json.decodeFromString(string)
    }

}
