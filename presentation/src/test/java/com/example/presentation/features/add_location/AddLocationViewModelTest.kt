package com.example.presentation.features.add_location

import androidx.lifecycle.viewModelScope
import com.example.common.KotlinTest
import com.example.domain.feature.location.search.LocationSearchUseCase
import com.example.domain.feature.location.store.LocationStoreUseCase
import com.example.presentation.ForecastsFactory
import com.example.presentation.features.add_location.model.AddLocationMessage
import com.example.presentation.forecasts
import com.example.presentation.snackbar.SnackBar
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.kotlin.any

@OptIn(ExperimentalCoroutinesApi::class)
class AddLocationViewModelTest : KotlinTest() {

    @Mock
    private lateinit var storeUseCase: LocationStoreUseCase

    @Mock
    private lateinit var searchUseCase: LocationSearchUseCase

    private val snackBar = SnackBar()
    private lateinit var viewModel: AddLocationViewModel

    private val factory: ForecastsFactory = forecasts
    private val locations = factory.createLocations(listOf("Porto", "Portland"))

    override fun init() {
        viewModel = AddLocationViewModel(
            snackBar = snackBar,
            storeUseCase = storeUseCase,
            searchUseCase = searchUseCase
        )
    }

    @Test
    fun `given search term changed, when success request, then results updated`() = runTest {

        Mockito.`when`(searchUseCase.search(any())).thenReturn(Result.success(locations))

        viewModel.onSearchChange(term = "porto")

        //suspend until search job finish
        viewModel.viewModelScope.coroutineContext[Job]!!.children.forEach { it.join() }

        assertEquals(locations, viewModel.searchResults.value)

    }

    @Test
    fun `given search term changed, when request error, then info message displayed`() = runTest {

        Mockito.`when`(searchUseCase.search(any())).thenReturn(Result.failure(Throwable()))

        viewModel.onSearchChange(term = "porto")

        //suspend until search job finish
        viewModel.viewModelScope.coroutineContext[Job]!!.children.forEach { it.join() }

        assertEquals(AddLocationMessage.ResultError, viewModel.infoMessage.first())
    }

    @Test
    fun `given search result, when clicked, it is stored in database`() = runTest {

        val location = locations.first()
        viewModel.onClickSave(location = location)

        Mockito.verify(storeUseCase).save(location)
    }

    @Test
    fun `given search result, when clicked, save message displayed`() = runTest {

        val location = locations.first()
        viewModel.onClickSave(location = location)

        assertEquals(AddLocationMessage.Save, viewModel.infoMessage.first())
    }

}