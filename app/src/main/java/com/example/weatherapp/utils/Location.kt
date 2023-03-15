package com.example.weatherapp.utils

import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.Priority

private const val REFRESH_INTERVAL: Long = 30 * 1000 // 30 seconds

internal val locationRequest: LocationRequest =
    LocationRequest.Builder(Priority.PRIORITY_BALANCED_POWER_ACCURACY, REFRESH_INTERVAL).build()