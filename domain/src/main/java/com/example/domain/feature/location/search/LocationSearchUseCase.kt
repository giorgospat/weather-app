package com.example.domain.feature.location.search

import com.example.domain.model.LocationModel

interface LocationSearchUseCase {

    suspend fun search(location: String): Result<List<LocationModel>>
}