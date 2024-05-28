package com.example.trackify.domain.model

data class Reminder(
    val time: Int,
    val isTurnedOn: Boolean = false
)
