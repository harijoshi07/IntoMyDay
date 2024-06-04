package com.example.trackify.ui.add_edit_screen

import com.example.trackify.domain.model.Task
import com.example.trackify.util.Priority
import java.time.LocalTime

sealed class AddEditScreenEvent {
    data class OnAddTaskClick(val task: Task) : AddEditScreenEvent()
    data class OnDeleteTaskClick(val task: Task) : AddEditScreenEvent()
    data class OnUpdateTitle(val title: String) : AddEditScreenEvent()
    data class OnUpdateStartTime(val startTime: LocalTime) : AddEditScreenEvent()
    data class OnUpdateEndTime(val endTime: LocalTime) : AddEditScreenEvent()
    data class OnUpdateReminder(val reminder: Boolean) : AddEditScreenEvent()
    data class OnUpdatePriority(val priority: Priority) : AddEditScreenEvent()
    class OnUpdateTask() : AddEditScreenEvent()
}