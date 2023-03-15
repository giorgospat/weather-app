package com.example.data.feature.home.cache

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ForecastCacheTest {

    private val cache: ForecastCache = ForecastCacheImpl()

    private val factory: ForecastsFactory = forecasts
    private val currentForecast = factory.createForecast(primary = true, locationId = "London")
    private val otherForecasts = listOf(
        factory.createForecast(primary = false, locationId = "Paris"),
        factory.createForecast(primary = false, locationId = "Athens")
    )

    @Test
    fun `given current forecast, when store, then successfully cached`() = runTest {
        cache.storeCurrentForecast(currentForecast)

        assertEquals(currentForecast, cache.currentForecast())
    }

    @Test
    fun `given other forecasts, when store, then successfully cached`() = runTest {
        cache.storeForecasts(otherForecasts)

        assertEquals(otherForecasts, cache.forecasts())
    }

    @Test
    fun `given forecast, when delete, then removed from cache`() = runTest {
        cache.storeForecasts(otherForecasts)
        cache.deleteForecast("Athens")
        val expected = otherForecasts.dropLast(1) //first item only, second deleted

        assertEquals(expected, cache.forecasts())
    }

    @Test
    fun `given forecast, when select by Id, then retrieved from cache`() = runTest {
        cache.storeForecasts(otherForecasts)
        val expected = otherForecasts[1]

        assertEquals(expected, cache.forecastById("Athens"))
    }

}