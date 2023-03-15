package com.example.data.feature.location

import com.example.common.annotation.IoDispatcher
import com.example.data.database.LocationDao
import com.example.data.feature.home.cache.ForecastCache
import com.example.domain.feature.location.store.LocationStoreRepository
import com.example.domain.model.LocationModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocationStoreRepositoryImpl @Inject constructor(
    private val dao: LocationDao,
    @IoDispatcher
    private val io: CoroutineDispatcher,
    private val cache: ForecastCache
) : LocationStoreRepository {

    override suspend fun locations(): Flow<List<LocationModel>> {
        return dao.getLocations().map { locations ->
            locations.toModel()
        }
    }

    override suspend fun save(location: LocationModel) {
        withContext(io) { dao.insertLocation(location.toEntity()) }
    }

    override suspend fun delete(location: LocationModel) {
        withContext(io) {
            dao.deleteLocation(location.toEntity())
            cache.deleteForecast(locationId = location.id)
        }
    }

    override suspend fun allowSave(limit: Int): Boolean {
        return withContext(io) { dao.getCount() < limit }
    }

}