package com.example.data.extensions

fun String.toHttps(): String {
    return when {
        this.contains("http://") -> {
            this.replace("http", "https")
        }
        this.startsWith("//") -> {
            "https:$this"
        }
        else -> this
    }
}