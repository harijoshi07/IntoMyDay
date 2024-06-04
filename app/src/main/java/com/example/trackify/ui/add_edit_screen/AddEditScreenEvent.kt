package com.example.trackify.ui.add_edit_screen

import com.example.trackify.domain.model.Task

sealed class AddEditScreenEvent {
    data class OnAddTaskClick(val task: Task):AddEditScreenEvent()
}