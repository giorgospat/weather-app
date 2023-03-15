package com.example.domain.model

data class LocationModel(
    val id: String,
    val name: String,
    val region: String,
    val country: String,
    val lat: Double,
    val lng: Double
) {
    companion object {
        val Empty = LocationModel(
            id = "",
            name = "",
            region = "",
            country = "",
            lat = Double.MIN_VALUE,
            lng = Double.MIN_VALUE
        )
    }
}

fun LocationModel.toText(): String {
    val builder = StringBuilder()

    if (this.name.isNotEmpty()) builder.append(this.name).append(", ")

    if (this.region.isNotEmpty()) builder.append(this.region).append(", ")

    if (this.country.isNotEmpty()) builder.append(this.name)

    return builder.toString()
}

fun LocationModel.toQuery(): String {
    return StringBuilder()
        .append(this.lat)
        .append(",")
        .append(this.lng)
        .toString()
}