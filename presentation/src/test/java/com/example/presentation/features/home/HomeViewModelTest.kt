package com.example.presentation.features.home

import com.example.common.KotlinTest
import com.example.domain.feature.home.ForecastUseCase
import com.example.domain.feature.location.store.LocationStoreUseCase
import com.example.domain.model.LatLng
import com.example.domain.model.LocationModel
import com.example.presentation.ForecastsFactory
import com.example.presentation.features.home.model.InfoMessage
import com.example.presentation.features.home.model.OptionType
import com.example.presentation.forecasts
import com.example.presentation.navigation.Screen
import com.example.presentation.snackbar.SnackBar
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runners.model.TestClass
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.kotlin.*

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest : KotlinTest() {

    @Mock
    private lateinit var forecastUseCase: ForecastUseCase

    @Mock
    private lateinit var locationStoreUseCase: LocationStoreUseCase

    private val snackBar = SnackBar()

    private lateinit var viewModel: HomeViewModel

    private val factory: ForecastsFactory = forecasts
    private val currentForecast =
        factory.createForecast(primary = true, location = "London", daysTemp = listOf(10.5))

    private val otherForecasts = listOf(
        factory.createForecast(primary = false, location = "London", daysTemp = listOf(14.5)),
        factory.createForecast(primary = false, location = "London", daysTemp = listOf(8.0))
    )

    private val locations = factory.createLocations(listOf("London", "Paris"))

    private val currentLatLng = LatLng(lat = 10.20, lng = 8.8)

    override fun init() {
        mockDependencies()
        initViewModel()
    }

    private fun initViewModel() {
        viewModel = HomeViewModel(
            snackBar = snackBar,
            forecastUseCase = forecastUseCase,
            locationStoreUseCase = locationStoreUseCase
        )
    }

    @Test
    fun `given current location, when updated, then displayed firsts in forecasts`() = runTest {
        Mockito.`when`(forecastUseCase.currentForecast(any(), any()))
            .thenReturn(Result.success(currentForecast))

        viewModel.refresh(location = currentLatLng)

        assertEquals(currentForecast, viewModel.allForecasts.value.first())
    }

    @Test
    fun `given locations, when new item added, then forecasts are fetched`() = runTest {

        val locationsFlow = MutableSharedFlow<List<LocationModel>>()

        Mockito.`when`(locationStoreUseCase.locations()).thenReturn(locationsFlow)

        initViewModel()

        //assert initial invoke from stubbing
        Mockito.verify(forecastUseCase, times(1)).forecasts(any(), any())

        //emit 2 new values
        locationsFlow.emit(locations)
        locationsFlow.emit(locations)

        //assert total invokes are 3
        Mockito.verify(forecastUseCase, times(3)).forecasts(any(), any())
    }

    @Test
    fun `given locations, when updated, then forecasts items are updated`() = runTest {

        Mockito.`when`(forecastUseCase.forecasts(any(), any())).thenReturn(otherForecasts)

        //create viewModel instance
        initViewModel()

        assertEquals(otherForecasts, viewModel.allForecasts.value)
    }

    @Test
    fun `given current location, when forecast request failed, then LocationError is displayed`() =
        runTest {

            Mockito.`when`(forecastUseCase.currentForecast(any(), any()))
                .thenReturn(Result.failure(Throwable()))

            //refresh screen with new location
            viewModel.refresh(location = currentLatLng)

            assertEquals(InfoMessage.LocationError, viewModel.infoMessage.first())
        }

    @Test
    fun `given option click, when add location and save allowed, then navigate to add location screen`() =
        runTest {

            Mockito.`when`(locationStoreUseCase.allowSave(any())).thenReturn(true)

            //click Add button
            viewModel.onClickOption(option = OptionType.Add, location = currentForecast.location)

            assertEquals(Pair(Screen.AddLocation, ""), viewModel.navigate.first())
        }


    @Test
    fun `given option click, when add location and save not allowed, then not allowed message displayed`() =
        runTest {

            Mockito.`when`(locationStoreUseCase.allowSave(any())).thenReturn(false)

            //click Add button
            viewModel.onClickOption(option = OptionType.Add, location = currentForecast.location)

            assertEquals(InfoMessage.SaveNotAvailable, viewModel.infoMessage.first())
        }

    @Test
    fun `given option click, when delete location, then delete message displayed`() =
        runTest {

            Mockito.`when`(locationStoreUseCase.allowSave(any())).thenReturn(false)

            //click Delete button
            viewModel.onClickOption(option = OptionType.Delete, location = currentForecast.location)

            assertEquals(InfoMessage.Delete, viewModel.infoMessage.first())
        }

    @Test
    fun `given option click, when location item, then navigate to location details screen`() =
        runTest {

            Mockito.`when`(locationStoreUseCase.allowSave(any())).thenReturn(false)

            //click location item
            val locationId = currentForecast.location.id
            viewModel.onLocationClick(locationId)

            assertEquals(Pair(Screen.Details, locationId), viewModel.navigate.first())
        }


    //region Mocked Data
    private fun mockDependencies() {
        val mockedObject: TestClass = mock()
        mockedObject.stub {
            onBlocking {
                locationStoreUseCase.locations()
            }.doReturn(flowOf(emptyList()))
            onBlocking {
                forecastUseCase.forecasts(any(), any())
            }.doReturn(emptyList())
        }
    }
    //endregion


}