package com.example.presentation.features.details

import androidx.lifecycle.SavedStateHandle
import com.example.common.KotlinTest
import com.example.domain.feature.home.ForecastUseCase
import com.example.presentation.features.details.DetailsViewModel
import com.example.presentation.navigation.Arguments
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.never
import org.mockito.kotlin.any

@OptIn(ExperimentalCoroutinesApi::class)
class DetailsViewModelTest: KotlinTest() {

    @Mock
    private lateinit var forecastUseCase: ForecastUseCase

    private lateinit var viewModel: DetailsViewModel


    override fun init() {}

    private fun initViewModel(state: SavedStateHandle) {
        viewModel = DetailsViewModel(
            state = state,
            useCase = forecastUseCase
        )
    }

    @Test
    fun `given viewModel, when init with locationId, then forecast is retrieved`() = runTest {

        val locationId = "London"
        val state = SavedStateHandle()
        state[Arguments.id] = locationId
        initViewModel(state)

        Mockito.verify(forecastUseCase).forecastById(locationId)
    }

    @Test
    fun `given viewModel, when locationId null, then forecastById is not invoked`() = runTest {

        val state = SavedStateHandle()
        initViewModel(state)

        Mockito.verify(forecastUseCase, never()).forecastById(any())
    }

}