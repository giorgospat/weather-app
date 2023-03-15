package com.example.domain.feature.location.store

import com.example.domain.model.LocationModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeLocationStoreRepository : LocationStoreRepository {

    private val locations = MutableStateFlow((emptyList<LocationModel>()))

    override suspend fun locations(): Flow<List<LocationModel>> = locations

    override suspend fun save(location: LocationModel) {
        val newList = locations.value + listOf(location)
        locations.emit(newList)
    }

    override suspend fun delete(location: LocationModel) {
        val newList = locations.value.filterNot { item -> item == location }
        locations.emit(newList)
    }

    override suspend fun allowSave(limit: Int): Boolean {
        return locations.value.size < limit
    }

}