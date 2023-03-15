package com.example.data.feature.home.cache

import com.example.domain.model.CurrentWeather
import com.example.domain.model.ForecastModel
import com.example.domain.model.LocationModel

internal interface ForecastsFactory {

    fun createForecast(primary: Boolean, locationId: String): ForecastModel
}

internal val forecasts = object : ForecastsFactory {

    override fun createForecast(primary: Boolean, locationId: String): ForecastModel {
        return ForecastModel(
            primary = primary,
            location = LocationModel.Empty.copy(id = locationId),
            current = CurrentWeather.Empty,
            forecast = emptyList()
        )
    }
}