package com.greencom.android.podcasts2.data.common

import androidx.room.TypeConverter
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds

class DurationTypeConverter {

    @TypeConverter
    fun fromDuration(duration: Duration): Long {
        return duration.inWholeMilliseconds
    }

    @TypeConverter
    fun toDuration(millis: Long): Duration {
        return millis.milliseconds
    }

}
