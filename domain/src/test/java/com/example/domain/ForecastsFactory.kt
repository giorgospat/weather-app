package com.example.domain

import com.example.domain.model.CurrentWeather
import com.example.domain.model.ForecastModel
import com.example.domain.model.LocationModel

internal interface ForecastsFactory {

    fun createForecast(
        primary: Boolean,
        locationId: String,
        lat: Double,
        lng: Double
    ): ForecastModel
}

internal val forecasts = object : ForecastsFactory {

    override fun createForecast(
        primary: Boolean,
        locationId: String,
        lat: Double,
        lng: Double
    ): ForecastModel {
        return ForecastModel(
            primary = primary,
            location = LocationModel(
                id = locationId,
                name = "",
                region = "",
                country = "",
                lat = lat,
                lng = lng
            ),
            current = CurrentWeather.Empty,
            forecast = emptyList()
        )
    }

}