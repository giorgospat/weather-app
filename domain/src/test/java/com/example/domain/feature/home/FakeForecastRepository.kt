package com.example.domain.feature.home

import com.example.domain.ForecastsFactory
import com.example.domain.model.ForecastModel
import com.example.domain.model.LatLng
import com.example.domain.model.LocationModel

class FakeForecastRepository : ForecastRepository {

    private val factory: ForecastsFactory = com.example.domain.forecasts

    private val current =
        factory.createForecast(primary = true, locationId = "Paris", lat = 1.0, lng = 2.0)

    private val otherForecasts = listOf(
        factory.createForecast(primary = false, locationId = "Porto", lat = 10.0, lng = 15.0),
        factory.createForecast(primary = false, locationId = "London", lat = 7.0, lng = 35.0)
    )

    override suspend fun currentForecast(location: LatLng, days: Int): Result<ForecastModel> {
        return Result.success(current)
    }

    override suspend fun forecasts(locations: List<LocationModel>, days: Int): List<ForecastModel> {
        return otherForecasts
    }

    override suspend fun forecastById(id: String): ForecastModel {
        return otherForecasts.find { forecast ->
            forecast.location.id == id
        } ?: ForecastModel.Empty
    }
}