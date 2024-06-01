package com.example.trackify.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.trackify.domain.LocalTimeConverter
import java.time.LocalTime
import java.time.format.DateTimeFormatter


@Entity(tableName = "tasks_tbl")
@TypeConverters(
    LocalTimeConverter::class,
)
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val isCompleted: Boolean,
    val startTime: LocalTime,
    val endTime: LocalTime,
    val reminderList: List<Reminder> = listOf(
        Reminder(5),
        Reminder(10),
        Reminder(15),
        Reminder(30)
    )
) {
    fun getFormattedTime(): String {
        val startTimeFormat = formatTime(startTime)
        val endTimeFormat = formatTime(endTime)
        return "$startTimeFormat - $endTimeFormat"
    }


    private fun formatTime(time: LocalTime): String {
        val dtf = DateTimeFormatter.ofPattern("hh:mm a")
        return time.format(dtf)
    }
}
