package com.example.trackify.ui.add_edit_screen

import com.example.trackify.domain.model.Task

sealed class AddEditScreenEvent {
    data class onAddTaskClick(val task: Task):AddEditScreenEvent()
}