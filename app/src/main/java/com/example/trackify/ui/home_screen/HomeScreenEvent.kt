package com.example.trackify.ui.home_screen

sealed class HomeScreenEvent {
    data class OnCompleted(val taskId: Int) : HomeScreenEvent()
}