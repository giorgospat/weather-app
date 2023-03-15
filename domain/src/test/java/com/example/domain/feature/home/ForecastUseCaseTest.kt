package com.example.domain.feature.home

import com.example.domain.ForecastsFactory
import com.example.domain.model.ForecastModel
import com.example.domain.model.LatLng
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ForecastUseCaseTest {

    private val repo: ForecastRepository = FakeForecastRepository()
    private val useCase: ForecastUseCase = ForecastUseCaseImpl(repo)
    private val factory: ForecastsFactory = com.example.domain.forecasts

    private val currentForecast =
        factory.createForecast(primary = true, locationId = "Paris", lat = 1.0, lng = 2.0)

    private val otherForecasts = listOf(
        factory.createForecast(primary = false, locationId = "Porto", lat = 10.0, lng = 15.0),
        factory.createForecast(primary = false, locationId = "London", lat = 7.0, lng = 35.0)
    )

    @Test
    fun `given current forecast, when response success, then result returned`() = runTest {
        val actual = useCase.currentForecast(
            location = LatLng(
                lat = currentForecast.location.lat,
                lng = currentForecast.location.lng
            ),
            days = 1
        )

        val expected = Result.success(currentForecast)

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `given forecasts, when response success, then results returned`() = runTest {
        val actual = useCase.forecasts(
            locations = otherForecasts.map { forecast ->
                forecast.location
            },
            days = 1
        )

        Assert.assertEquals(otherForecasts, actual)
    }

    @Test
    fun `given forecasts by id, when found, then result returned`() = runTest {
        val actual = useCase.forecastById(otherForecasts.first().location.id)

        val expected = otherForecasts.first()

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `given forecasts by id, when not found, then empty result returned`() = runTest {
        val actual = useCase.forecastById("anyId")

        val expected = ForecastModel.Empty

        Assert.assertEquals(expected, actual)
    }


}