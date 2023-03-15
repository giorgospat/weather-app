package com.example.domain.feature.location.store

import com.example.domain.model.LocationModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocationStoreUseCaseImpl @Inject constructor(
    private val locationRepository: LocationStoreRepository
) : LocationStoreUseCase {

    override suspend fun locations(): Flow<List<LocationModel>> {
        return locationRepository.locations()
    }

    override suspend fun save(location: LocationModel) {
        locationRepository.save(location = location)
    }

    override suspend fun delete(location: LocationModel) {
        locationRepository.delete(location = location)
    }

    override suspend fun allowSave(limit: Int): Boolean {
        return locationRepository.allowSave(limit)
    }
}