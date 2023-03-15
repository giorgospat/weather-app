package com.example.data.backend

interface BackendConfigProvider {

    fun baseUrl(): String
    fun apiKey(): String
}