package com.example.data.feature.home

import com.example.data.feature.location.LocationApiModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ForecastApiModel(
    @Json(name = "current")
    val current: Current,
    @Json(name = "forecast")
    val forecast: Forecast,
    @Json(name = "location")
    val location: LocationApiModel
)

@JsonClass(generateAdapter = true)
data class Current(
    @Json(name = "condition")
    val condition: Condition?,
    @Json(name = "last_updated_epoch")
    val lastUpdatedEpoch: Long?,
    @Json(name = "temp_c")
    val tempC: Double?
)

@JsonClass(generateAdapter = true)
data class Forecast(
    @Json(name = "forecastday")
    val forecastDay: List<ForecastDay>?
)

@JsonClass(generateAdapter = true)
data class Condition(
    @Json(name = "code")
    val code: Int?,
    @Json(name = "icon")
    val icon: String?,
    @Json(name = "text")
    val text: String?
)

@JsonClass(generateAdapter = true)
data class ForecastDay(
    @Json(name = "astro")
    val astro: Astro?,
    @Json(name = "date_epoch")
    val dateEpoch: Long?,
    @Json(name = "day")
    val day: Day?
)

@JsonClass(generateAdapter = true)
data class Astro(
    val sunrise: String?,
    @Json(name = "sunset")
    val sunset: String?
)

@JsonClass(generateAdapter = true)
data class Day(
    @Json(name = "avghumidity")
    val avghumidity: Double?,
    @Json(name = "avgtemp_c")
    val avgtempC: Double?,
    @Json(name = "avgvis_km")
    val avgvisKm: Double?,
    @Json(name = "condition")
    val condition: Condition?,
    @Json(name = "maxtemp_c")
    val maxtempC: Double?,
    @Json(name = "maxwind_kph")
    val maxwindKph: Double?,
    @Json(name = "mintemp_c")
    val mintempC: Double?,
    @Json(name = "uv")
    val uv: Double?
)