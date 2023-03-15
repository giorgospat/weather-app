package com.example.data.feature.home

import com.example.common.annotation.IoDispatcher
import com.example.data.backend.WeatherApi
import com.example.data.feature.home.cache.ForecastCache
import com.example.data.utils.wrapRequest
import com.example.domain.feature.home.ForecastRepository
import com.example.domain.model.ForecastModel
import com.example.domain.model.LatLng
import com.example.domain.model.LocationModel
import com.example.domain.model.toQuery
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext
import javax.inject.Inject


class ForecastRepositoryImpl @Inject constructor(
    @IoDispatcher
    private val io: CoroutineDispatcher,
    private val api: WeatherApi,
    private val cache: ForecastCache
) : ForecastRepository {

    override suspend fun currentForecast(location: LatLng, days: Int): Result<ForecastModel> {

        //check if already cached
        cache.currentForecast()?.let { forecast ->
            return Result.success(forecast)
        }

        //else fetch from backend
        return withContext(io) {
            wrapRequest {
                api.forecast(location = location.toQuery(), days = days)
            }.map { forecast ->
                forecast.toModel(primary = true).also { forecastModel ->
                    cache.storeCurrentForecast(forecastModel)
                }
            }
        }
    }

    override suspend fun forecasts(locations: List<LocationModel>, days: Int): List<ForecastModel> {

        //get already cached forecasts
        val cached = cache.forecasts()

        //else fetch from backend
        return withContext(io) {
            locations.map { location ->

                async {
                    /**
                     * if forecast already cached skip it,
                     * else fetch it from backend
                     */
                    val cachedForecast = cached.find {
                        it.location == location
                    }

                    if (cachedForecast != null) {
                        Result.success(cachedForecast)
                    } else {
                        wrapRequest {
                            api.forecast(location = location.toQuery(), days = days)
                        }.map { forecast ->
                            forecast.toModel(primary = false)
                        }
                    }
                }

            }.awaitAll().mapNotNull { result ->
                //return only successful responses (or implement other instructed business logic)
                if (result.isSuccess) result.getOrNull() else null
            }.also { forecasts ->
                cache.storeForecasts(forecasts) //save forecasts in cache
            }
        }
    }

    override suspend fun forecastById(id: String): ForecastModel {
        return cache.forecastById(id = id) ?: ForecastModel.Empty
    }

}