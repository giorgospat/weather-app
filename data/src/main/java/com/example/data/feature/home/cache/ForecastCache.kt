package com.example.data.feature.home.cache

import com.example.domain.model.ForecastModel

interface ForecastCache {

    suspend fun storeCurrentForecast(forecast: ForecastModel)

    suspend fun storeForecasts(forecasts: List<ForecastModel>)

    suspend fun deleteForecast(locationId: String)

    suspend fun currentForecast(): ForecastModel?

    suspend fun forecasts(): List<ForecastModel>

    suspend fun forecastById(id: String): ForecastModel?

}