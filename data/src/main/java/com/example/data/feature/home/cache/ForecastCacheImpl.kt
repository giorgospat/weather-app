package com.example.data.feature.home.cache

import com.example.domain.model.ForecastModel
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject

class ForecastCacheImpl @Inject constructor() : ForecastCache {

    private val forecastMutex = Mutex()
    private val forecastsMutex = Mutex()

    private var currentForecast: ForecastModel? = null
    private var forecasts: List<ForecastModel> = emptyList()

    override suspend fun storeCurrentForecast(forecast: ForecastModel) {
        forecastMutex.withLock {
            currentForecast = forecast
        }
    }

    override suspend fun storeForecasts(forecasts: List<ForecastModel>) {
        forecastsMutex.withLock {
            this.forecasts = forecasts
        }
    }

    override suspend fun deleteForecast(locationId: String) {
        forecastsMutex.withLock {
            this.forecasts = forecasts.filterNot { forecast ->
                forecast.location.id == locationId
            }
        }
    }

    override suspend fun currentForecast(): ForecastModel? {
        return currentForecast
    }

    override suspend fun forecasts(): List<ForecastModel> {
        return forecasts
    }

    override suspend fun forecastById(id: String): ForecastModel? {
        return if (currentForecast?.location?.id == id) {
            currentForecast
        } else {
            forecasts.find { forecast ->
                forecast.location.id == id
            }
        }
    }

}