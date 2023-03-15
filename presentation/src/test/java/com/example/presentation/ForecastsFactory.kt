package com.example.presentation

import com.example.domain.model.*
import kotlin.random.Random

internal interface ForecastsFactory {

    fun createForecast(primary: Boolean, location: String, daysTemp: List<Double>): ForecastModel
    fun createLocations(names: List<String>): List<LocationModel>
}

internal val forecasts = object : ForecastsFactory {

    override fun createForecast(
        primary: Boolean,
        location: String,
        daysTemp: List<Double>
    ): ForecastModel {
        return ForecastModel(
            primary = primary,
            location = location(name = location),
            current = current(),
            forecast = dayForecasts(daysTemp)
        )
    }

    override fun createLocations(names: List<String>): List<LocationModel> {
        return names.map { name ->
            location(name)
        }
    }

    private fun location(
        name: String = "",
        region: String = "",
        country: String = "",
        lat: Double = 0.0,
        lng: Double = 0.0
    ): LocationModel {
        return LocationModel(
            id = Random.nextInt().toString(),
            name = name,
            region = region,
            country = country,
            lat = lat,
            lng = lng
        )
    }

    private fun current(temp: Double = 0.0): CurrentWeather {
        return CurrentWeather(
            lastUpdatedMillis = System.currentTimeMillis(),
            tempCelsius = temp,
            condition = Condition.Empty
        )
    }

    private fun dayForecasts(averageTemp: List<Double>): List<DayForecast> {
        return averageTemp.map { temp ->
            DayForecast(
                dateMillis = System.currentTimeMillis(),
                averageTempCelsius = temp,
                maxTempCelsius = 0.0,
                minTempCelsius = 0.0,
                condition = Condition.Empty,
                humidity = 0.0,
                uv = 0.0,
                windKm = 0.0,
                visibilityKm = 0.0,
                sunrise = "",
                sunset = "",
            )
        }
    }

}