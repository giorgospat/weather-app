package com.example.domain.feature.location.search

import com.example.domain.LocationModelFactory
import com.example.domain.feature.location.LocationRepository
import com.example.domain.locationFactory
import com.example.domain.model.LocationModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class LocationSearchUseCaseTest {

    private val repo: LocationRepository = FakeLocationRepository()
    private val useCase: LocationSearchUseCase = LocationSearchUseCaseImpl(repo)
    private val factory: LocationModelFactory = locationFactory
    private val searchResults = listOf(
        factory.location(id = "123", name = "Porto"),
        factory.location(id = "123", name = "Portland")
    )

    @Test
    fun `given search term, when many locations found, then results returned`() = runTest {
        val actual = useCase.search(location = "por")

        val expected = Result.success(searchResults)

        assertEquals(expected, actual)
    }

    @Test
    fun `given search term, when one location found, then result returned`() = runTest {
        val actual = useCase.search(location = "porto")

        val expected = Result.success(searchResults.dropLast(1))

        assertEquals(expected, actual)
    }

    @Test
    fun `given search term, when locations not found, then no results returned`() = runTest {
        val actual = useCase.search(location = "athens")

        val expected = Result.success(emptyList<LocationModel>())

        assertEquals(expected, actual)
    }


}