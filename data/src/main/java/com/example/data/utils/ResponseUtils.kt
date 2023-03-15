package com.example.data.utils

import retrofit2.Response

internal suspend fun <T> wrapRequest(
    request: suspend () -> Response<T>
): Result<T> {
    return try {
        val response = request()
        val body = response.body()
        if (response.isSuccessful && body != null) {
            Result.success(body)
        } else {
            Result.failure(IllegalStateException(response.errorBody().toString()))
        }
    } catch (ex: Exception) {
        Result.failure(ex)
    }
}