package com.example.data.feature.location

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LocationApiModel(
    @Json(name = "country")
    val country: String,
    @Json(name = "lat")
    val lat: Double,
    @Json(name = "lon")
    val lon: Double,
    @Json(name = "name")
    val name: String,
    @Json(name = "region")
    val region: String
) {

    //build custom Id using lat-long, since backend doesn't provide for forecast locations
    internal fun locationId(): String {
        return with(this) { "$lat$lon" }
    }

}