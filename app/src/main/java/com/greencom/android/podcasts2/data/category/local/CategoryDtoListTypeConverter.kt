package com.greencom.android.podcasts2.data.category.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.greencom.android.podcasts2.data.category.local.dto.CategoryDto
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@ProvidedTypeConverter
class CategoryDtoListTypeConverter(private val json: Json) {

    @TypeConverter
    fun fromCategoryDtoList(categories: List<CategoryDto>): String {
        return json.encodeToString(categories)
    }

    @TypeConverter
    fun toCategoryDtoList(string: String): List<CategoryDto> {
        return json.decodeFromString(string)
    }

}