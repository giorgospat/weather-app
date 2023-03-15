package com.example.weatherapp

import android.location.Location
import com.example.domain.model.LatLng
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock

class MainViewModelTest {

    private val viewModel = MainViewModel()
    private val lat = 20.5
    private val lng = 55.10
    private val latLng = LatLng(lat, lng)

    @Test
    fun `given current location, when changed, then view is updated`() {
        val location = mock(Location::class.java)
        Mockito.`when`(location.latitude).thenReturn(lat)
        Mockito.`when`(location.longitude).thenReturn(lng)

        viewModel.updateLocation(location = location)
        assertEquals(latLng, viewModel.location.value)
    }
}