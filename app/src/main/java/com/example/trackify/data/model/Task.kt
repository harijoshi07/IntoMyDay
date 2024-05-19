package com.example.trackify.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks_tbl")
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val isCompleted: Boolean,
    val startTime: Long,
    val endTime: Long
)
