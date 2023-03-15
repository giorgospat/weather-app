package com.example.domain.model

data class ForecastModel(
    val primary: Boolean,
    val location: LocationModel,
    val current: CurrentWeather,
    val forecast: List<DayForecast>
) {
    companion object {
        val Empty =
            ForecastModel(
                primary = false,
                location = LocationModel.Empty,
                current = CurrentWeather.Empty,
                forecast = emptyList()
            )
    }
}

data class CurrentWeather(
    val lastUpdatedMillis: Long?,
    val tempCelsius: Double?,
    val condition: Condition
) {
    companion object {
        val Empty = CurrentWeather(
            lastUpdatedMillis = null,
            tempCelsius = null,
            condition = Condition.Empty
        )
    }
}

data class DayForecast(
    val dateMillis: Long?,
    val averageTempCelsius: Double?,
    val maxTempCelsius: Double?,
    val minTempCelsius: Double?,
    val condition: Condition,
    val humidity: Double?,
    val uv: Double?,
    val windKm: Double?,
    val visibilityKm: Double?,
    val sunrise: String?,
    val sunset: String?,
)

data class Condition(
    val text: String,
    val icon: String,
) {
    companion object {
        val Empty = Condition(text = "", icon = "")
    }
}