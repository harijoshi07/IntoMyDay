package com.example.trackify.test

import java.io.File

fun main() {
    val input = File("/Users/harijoshi/AndroidStudioProjects/Trackify/app/src/main/java/com/example/trackify/test/input.txt")
            .readText()

    // Regular expression to find ports
    val portFiltering = Regex("""\b\d+/tcp\b""")

    // print port numbers
    val ports = portFiltering.findAll(input).map { it.value.split("/")[0] }
    println(ports.joinToString(", "))
}


