package com.example.trackify.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Entity(tableName = "tasks_tbl")
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int=0,
    val title: String,
    val isCompleted: Boolean,
    val startTime: Long,
    val endTime: Long
) {
    fun getFormattedTime(): String {
        val startTimeFormat = formatTime(startTime)
        val endTimeFormat = formatTime(endTime)
        return "$startTimeFormat - $endTimeFormat"
    }


    private fun formatTime(time: Long): String {
        val timeFormatter = SimpleDateFormat("hh:mm a", Locale.getDefault())
        val date = Date(time)   //converts time in long into date object
        return timeFormatter.format(date)

    }
}
