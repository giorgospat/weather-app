package com.example.presentation.features.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.feature.home.ForecastUseCase
import com.example.domain.model.ForecastModel
import com.example.presentation.navigation.Arguments
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    state: SavedStateHandle,
    private val useCase: ForecastUseCase
) : ViewModel() {

    private var _forecast = MutableStateFlow(ForecastModel.Empty)
    internal val forecast = _forecast.asStateFlow()

    init {
        val locationId = state.get<String>(Arguments.id)
        locationId?.let { id ->
            viewModelScope.launch {
                _forecast.value = useCase.forecastById(id = id)
            }
        }
    }

}