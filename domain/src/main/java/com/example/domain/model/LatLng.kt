package com.example.domain.model

data class LatLng(
    val lat: Double,
    val lng: Double
) {
    companion object {
        val Empty = LatLng(lat = Double.MIN_VALUE, lng = Double.MIN_VALUE)
    }
}

fun LatLng.toQuery(): String {
    return StringBuilder()
        .append(this.lat)
        .append(",")
        .append(this.lng)
        .toString()
}