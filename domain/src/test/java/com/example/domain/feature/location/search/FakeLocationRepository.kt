package com.example.domain.feature.location.search

import com.example.domain.LocationModelFactory
import com.example.domain.feature.location.LocationRepository
import com.example.domain.locationFactory
import com.example.domain.model.LocationModel

class FakeLocationRepository : LocationRepository {

    private val factory: LocationModelFactory = locationFactory
    private val searchResults = listOf(
        factory.location(id = "123", name = "Porto"),
        factory.location(id = "123", name = "Portland")
    )

    override suspend fun searchLocation(location: String): Result<List<LocationModel>> {
        return Result.success(
            searchResults.filter { result ->
                result.name.contains(other = location, ignoreCase = true)
            }
        )
    }

}