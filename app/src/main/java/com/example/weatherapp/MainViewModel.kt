package com.example.weatherapp

import android.location.Location
import androidx.lifecycle.ViewModel
import com.example.domain.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private var _location = MutableStateFlow(LatLng.Empty)
    internal val location = _location.asStateFlow()

    internal fun updateLocation(location: Location) {
        _location.tryEmit(LatLng(lat = location.latitude, lng = location.longitude))
    }

}