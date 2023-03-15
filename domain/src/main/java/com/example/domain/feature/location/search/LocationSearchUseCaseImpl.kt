package com.example.domain.feature.location.search

import com.example.domain.feature.location.LocationRepository
import com.example.domain.model.LocationModel
import javax.inject.Inject

class LocationSearchUseCaseImpl @Inject constructor(
    private val repository: LocationRepository
) : LocationSearchUseCase {

    override suspend fun search(location: String): Result<List<LocationModel>> {
        return try {
            repository.searchLocation(location)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}