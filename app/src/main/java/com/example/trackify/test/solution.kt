package com.example.trackify.test

import java.io.File

fun main() {
    val input = File("i.txt").readText()

    val ports = input.split(Regex("""\D+"""))
        .filter { it.isNotBlank() && it.toIntOrNull() != null }
        .joinToString(", ")
    println(ports)
}
