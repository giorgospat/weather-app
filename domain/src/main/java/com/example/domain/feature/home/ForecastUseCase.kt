package com.example.domain.feature.home

import com.example.domain.model.ForecastModel
import com.example.domain.model.LatLng
import com.example.domain.model.LocationModel

interface ForecastUseCase {

    suspend fun currentForecast(location: LatLng, days: Int): Result<ForecastModel>

    suspend fun forecasts(locations: List<LocationModel>, days: Int): List<ForecastModel>

    suspend fun forecastById(id: String): ForecastModel

}