package com.example.presentation.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.feature.home.ForecastUseCase
import com.example.domain.feature.location.store.LocationStoreUseCase
import com.example.domain.model.ForecastModel
import com.example.domain.model.LatLng
import com.example.domain.model.LocationModel
import com.example.presentation.features.home.model.InfoMessage
import com.example.presentation.features.home.model.OptionType
import com.example.presentation.navigation.Screen
import com.example.presentation.snackbar.SnackBar
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    internal val snackBar: SnackBar,
    private val forecastUseCase: ForecastUseCase,
    private val locationStoreUseCase: LocationStoreUseCase
) : ViewModel() {

    private var currentForecast = MutableStateFlow(ForecastModel.Empty)
    private var savedForecasts = MutableStateFlow(emptyList<ForecastModel>())

    private var _allForecasts = MutableStateFlow(emptyList<ForecastModel>())
    internal val allForecasts = _allForecasts.asStateFlow()

    private var _infoMessage = Channel<InfoMessage>(Channel.BUFFERED)
    internal val infoMessage = _infoMessage.receiveAsFlow()

    private var _loading = MutableStateFlow(false)
    internal val loading = _loading.asStateFlow()

    private var _navigate = Channel<Pair<Screen, String>>()
    internal val navigate = _navigate.receiveAsFlow()

    private val daysAhead = 10 //days
    private val storedLocationLimit = 5 //max 5 other locations stored

    init {
        observeLocations()
    }

    internal fun refresh(location: LatLng) {
        viewModelScope.launch {
            if (location != LatLng.Empty) {
                getForecast(location = location)
            }
        }
    }

    private fun observeLocations() {
        viewModelScope.launch {
            locationStoreUseCase.locations()
                .onEach { locations ->
                    getForecasts(locations = locations)
                }
                .launchIn(viewModelScope)
        }
    }

    private suspend fun getForecast(location: LatLng) {
        _loading.value = true

        forecastUseCase.currentForecast(location = location, days = daysAhead).fold(
            onSuccess = { forecast ->
                currentForecast.value = forecast
                updateLocations()
            },
            onFailure = {
                _infoMessage.send(InfoMessage.LocationError)
            }
        )

        _loading.value = false
    }

    private suspend fun getForecasts(locations: List<LocationModel>) {
        savedForecasts.value = forecastUseCase.forecasts(locations = locations, days = daysAhead)
        updateLocations()
    }

    /** Display the list where first item is current location forecast
     * and other items are saved locations' forecasts */
    private fun updateLocations() {
        val allForecasts = arrayListOf<ForecastModel>()
        if (currentForecast.value != ForecastModel.Empty) allForecasts.add(currentForecast.value)
        allForecasts.addAll(savedForecasts.value)

        _allForecasts.value = allForecasts
    }

    internal fun onClickOption(option: OptionType, location: LocationModel) {
        viewModelScope.launch {
            when (option) {
                OptionType.Add -> {
                    if (locationStoreUseCase.allowSave(limit = storedLocationLimit)) {
                        _navigate.send(Screen.AddLocation to "")
                    } else {
                        _infoMessage.send(InfoMessage.SaveNotAvailable)
                    }
                }
                OptionType.Delete -> {
                    locationStoreUseCase.delete(location = location)
                    _infoMessage.send(InfoMessage.Delete)
                }
            }
        }
    }

    internal fun onLocationClick(id: String) {
        viewModelScope.launch {
            _navigate.send(Screen.Details to id)
        }
    }

}