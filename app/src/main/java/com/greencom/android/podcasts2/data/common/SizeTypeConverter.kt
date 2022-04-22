package com.greencom.android.podcasts2.data.common

import androidx.room.TypeConverter
import com.greencom.android.podcasts2.utils.Size
import com.greencom.android.podcasts2.utils.Size.Companion.bytes

class SizeTypeConverter {

    @TypeConverter
    fun fromSize(size: Size): Long = size.inBytes

    @TypeConverter
    fun toSize(bytes: Long): Size = bytes.bytes

}
