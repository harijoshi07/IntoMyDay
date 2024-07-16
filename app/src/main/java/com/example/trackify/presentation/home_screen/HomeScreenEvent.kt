package com.example.trackify.presentation.home_screen

sealed class HomeScreenEvent {
    data class OnCompleted(val taskId: Int, val isCompleted: Boolean) : HomeScreenEvent()
}