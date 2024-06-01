package com.example.trackify.domain

import androidx.room.TypeConverter
import com.example.trackify.domain.model.Reminder
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/*
- Convert object of Reminder type to String type
-Room only supports storing simple data types directly in the database.
- like int, bool, string, etc.
-We cannot directly store objects of Reminder type in db.
-This is where TypeConverter comes in handy.
-Serialization is converting complex data type into simple data  type b4 storing it to db
-e.g. Reminder class to String
-Deserialization is converting simple data type back to complex data type when reading from db
*/
object ReminderListConverter {
    @TypeConverter
    fun fromReminderList(reminderList: List<Reminder>): String {
        val gson = Gson()
        return gson.toJson(reminderList)
    }

    @TypeConverter
    fun toReminderList(reminderListString: String): List<Reminder> {
        val gson = Gson()
        val type = object : TypeToken<List<Reminder>>() {}.type
        return gson.fromJson(
            reminderListString,
            type
        )
    }
}