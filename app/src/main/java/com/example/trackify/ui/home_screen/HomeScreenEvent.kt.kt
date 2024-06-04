package com.example.trackify.ui.home_screen

sealed class HomeScreenEvent {
    data class onCompleted(val id: Int) : HomeScreenEvent()
}