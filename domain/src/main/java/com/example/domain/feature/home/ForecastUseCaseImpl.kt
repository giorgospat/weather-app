package com.example.domain.feature.home

import com.example.domain.model.ForecastModel
import com.example.domain.model.LatLng
import com.example.domain.model.LocationModel
import javax.inject.Inject

class ForecastUseCaseImpl @Inject constructor(
    private val repo: ForecastRepository
) : ForecastUseCase {

    override suspend fun currentForecast(location: LatLng, days: Int): Result<ForecastModel> {
        return try {
            repo.currentForecast(location = location, days = days)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun forecasts(locations: List<LocationModel>, days: Int): List<ForecastModel> {
        return try {
            repo.forecasts(locations = locations, days = days)
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun forecastById(id: String): ForecastModel {
        return repo.forecastById(id = id)
    }

}