package com.example.data.feature.location

import com.example.common.annotation.IoDispatcher
import com.example.data.backend.WeatherApi
import com.example.data.utils.wrapRequest
import com.example.domain.feature.location.LocationRepository
import com.example.domain.model.LocationModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private val api: WeatherApi,
    @IoDispatcher
    private val io: CoroutineDispatcher
) : LocationRepository {

    override suspend fun searchLocation(location: String): Result<List<LocationModel>> {
        return withContext(io) {
            wrapRequest {
                api.search(location)
            }.map { results ->
                results.toModel()
            }
        }
    }
}