package com.example.domain.feature.location

import com.example.domain.model.LocationModel

interface LocationRepository {

    suspend fun searchLocation(location: String): Result<List<LocationModel>>
}