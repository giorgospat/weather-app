package com.example.data.feature.home

import com.example.data.extensions.toHttps
import com.example.data.feature.location.LocationApiModel
import com.example.domain.feature.home.*
import com.example.domain.model.*
import java.util.*
import com.example.data.feature.home.Condition as ConditionApiModel

internal fun ForecastApiModel.toModel(primary: Boolean): ForecastModel {
    return ForecastModel(
        primary = primary,
        location = this.location.toModel(),
        current = this.current.toModel(),
        forecast = this.forecast.forecastDay?.toModel() ?: emptyList()
    )
}

private fun LocationApiModel.toModel(): LocationModel {
    return LocationModel(
        id = this.locationId(),
        name = this.name,
        region = this.region,
        country = this.country,
        lat = this.lat,
        lng = this.lon
    )
}

private fun Current.toModel(): CurrentWeather {
    return CurrentWeather(
        lastUpdatedMillis = this.lastUpdatedEpoch,
        tempCelsius = this.tempC,
        condition = this.condition?.toModel() ?: Condition.Empty
    )
}

private fun ConditionApiModel.toModel(): Condition {
    return Condition(
        text = this.text ?: "",
        icon = this.icon?.toHttps() ?: ""
    )
}

@JvmName("ForecastDayToModel")
private fun List<ForecastDay>.toModel(): List<DayForecast> {
    return this.map { forecast ->
        DayForecast(
            dateMillis = forecast.dateEpoch,
            averageTempCelsius = forecast.day?.avgtempC,
            maxTempCelsius = forecast.day?.maxtempC,
            minTempCelsius = forecast.day?.mintempC,
            condition = forecast.day?.condition?.toModel() ?: Condition.Empty,
            humidity = forecast.day?.avghumidity,
            uv = forecast.day?.uv,
            windKm = forecast.day?.maxwindKph,
            visibilityKm = forecast.day?.avgvisKm,
            sunrise = forecast.astro?.sunrise,
            sunset = forecast.astro?.sunset,
        )
    }
}