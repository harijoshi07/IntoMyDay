package com.example.trackify.domain

import androidx.room.TypeConverter
import java.time.LocalTime

//@TypeConverter is used by room to handle conversion of data types.
//"parse" means converting a string representation of a time("12:10:05") into a LocalTime object.
object LocalTimeConverter {

    @TypeConverter
    @JvmStatic
    fun fromString(value: String?): LocalTime? {
        return value?.let {
            LocalTime.parse(value)
        }
    }

    @TypeConverter
    @JvmStatic
    fun toString(localTime: LocalTime?): String? {
        return localTime?.toString()
    }
}