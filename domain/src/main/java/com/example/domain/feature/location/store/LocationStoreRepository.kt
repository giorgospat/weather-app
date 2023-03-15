package com.example.domain.feature.location.store

import com.example.domain.model.LocationModel
import kotlinx.coroutines.flow.Flow

interface LocationStoreRepository {

    suspend fun locations(): Flow<List<LocationModel>>

    suspend fun save(location: LocationModel)

    suspend fun delete(location: LocationModel)

    suspend fun allowSave(limit: Int): Boolean

}