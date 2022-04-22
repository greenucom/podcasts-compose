package com.greencom.android.podcasts2.data.category.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@ProvidedTypeConverter
class CategoryEntityDtoListTypeConverter(private val json: Json) {

    @TypeConverter
    fun fromCategoryEntityDtoList(categories: List<CategoryEntityDto>): String {
        return json.encodeToString(categories)
    }

    @TypeConverter
    fun toCategoryEntityDtoList(string: String): List<CategoryEntityDto> {
        return json.decodeFromString(string)
    }

}
