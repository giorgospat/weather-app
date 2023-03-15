package com.example.domain

import com.example.domain.model.LocationModel

 internal interface LocationModelFactory {

    fun location(id: String, name: String): LocationModel
}

 internal object locationFactory : LocationModelFactory {

    override fun location(id: String, name: String): LocationModel {
        return LocationModel(
            id = id,
            name = name,
            region = "",
            country = "",
            lat = Double.MIN_VALUE,
            lng = Double.MIN_VALUE
        )
    }

}