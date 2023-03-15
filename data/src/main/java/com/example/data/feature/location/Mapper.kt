package com.example.data.feature.location

import com.example.data.database.LocationEntity
import com.example.domain.model.LocationModel

internal fun List<LocationEntity>.toModel(): List<LocationModel> {
    return this.map { location ->
        LocationModel(
            id = location.id,
            name = location.name,
            region = location.region,
            country = location.country,
            lat = location.lat,
            lng = location.lng
        )
    }
}

internal fun LocationModel.toEntity(): LocationEntity {
    return LocationEntity(
        id = this.id,
        name = this.name,
        region = this.region,
        country = this.country,
        lat = this.lat,
        lng = this.lng
    )
}

@JvmName("LocationApiModelToModel")
internal fun List<LocationApiModel>.toModel(): List<LocationModel> {
    return this.map { item ->
        LocationModel(
            id = item.locationId(),
            name = item.name,
            region = item.region,
            country = item.country,
            lat = item.lat,
            lng = item.lon
        )
    }
}