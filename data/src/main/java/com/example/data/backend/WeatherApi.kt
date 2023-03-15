package com.example.data.backend

import com.example.data.feature.home.ForecastApiModel
import com.example.data.feature.location.LocationApiModel
import retrofit2.Response
import retrofit2.http.HTTP
import retrofit2.http.Query

interface WeatherApi {

    @HTTP(method = "GET", path = "forecast.json")
    suspend fun forecast(
        @Query("q") location: String,
        @Query("days") days: Int
    ): Response<ForecastApiModel>

    @HTTP(method = "GET", path = "search.json")
    suspend fun search(@Query("q") location: String): Response<List<LocationApiModel>>

}