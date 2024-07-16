package com.example.trackify.presentation

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.trackify.R

val fontRoboto = FontFamily(Font(R.font.roboto))
val fontMontserrat = FontFamily(Font(R.font.montserrat))

var h1TextStyle = TextStyle(
    fontSize = 24.sp,
    fontFamily = fontMontserrat,
    fontWeight = FontWeight.Bold
)

var h2TextStyle = TextStyle(
    fontSize = 20.sp,
    fontFamily = fontMontserrat,
    fontWeight = FontWeight.Bold
)

var infoTextStyle = TextStyle(
    fontSize = 18.sp,
    fontFamily = fontMontserrat,
    fontWeight = FontWeight.SemiBold
)

var infoDescTextStyle = TextStyle(
    fontSize = 14.sp,
    fontFamily = fontRoboto,
    fontWeight = FontWeight.Normal
)

var taskTextStyle = TextStyle(
    fontSize = 16.sp,
    fontFamily = fontRoboto,
    fontWeight = FontWeight.Normal
)


var taskDescTextStyle = TextStyle(
    fontSize = 10.sp,
    fontFamily = fontRoboto,
    fontWeight = FontWeight.Normal
)

var compTextStyle = TextStyle(
    fontSize = 16.sp,
    fontWeight = FontWeight.SemiBold,
    fontFamily = FontFamily.Monospace,
    color = Color.White
)

var timerTextStyle = TextStyle(
    fontSize = 42.sp,
    fontFamily = fontMontserrat
)