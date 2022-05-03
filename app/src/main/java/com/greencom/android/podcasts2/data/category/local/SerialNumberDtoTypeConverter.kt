package com.greencom.android.podcasts2.data.category.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.greencom.android.podcasts2.data.episode.local.SerialNumberDto
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@ProvidedTypeConverter
class SerialNumberDtoTypeConverter(private val json: Json) {

    @TypeConverter
    fun fromSerialNumberDto(serialNumber: SerialNumberDto): String {
        return json.encodeToString(serialNumber)
    }

    @TypeConverter
    fun toSerialNumberDto(string: String): SerialNumberDto {
        return json.decodeFromString(string)
    }

}
