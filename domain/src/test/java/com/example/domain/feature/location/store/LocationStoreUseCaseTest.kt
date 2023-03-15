package com.example.domain.feature.location.store

import com.example.domain.LocationModelFactory
import com.example.domain.locationFactory
import com.example.domain.model.LocationModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test
import kotlin.random.Random

@OptIn(ExperimentalCoroutinesApi::class)
class LocationStoreUseCaseTest {

    private val repo: LocationStoreRepository = FakeLocationStoreRepository()
    private val useCase: LocationStoreUseCase = LocationStoreUseCaseImpl(repo)

    private val factory: LocationModelFactory = locationFactory
    private val location = factory.location(id = "123", name = "London")
    private val saveLimit = 5

    @Test
    fun `given locations, when new location saved, then flow is updated`() = runTest {
        useCase.save(location)

        assertEquals(listOf(location), useCase.locations().first())
    }

    @Test
    fun `given locations, when location deleted, then flow is updated`() = runTest {
        useCase.save(location)
        useCase.delete(location)

        assertEquals(emptyList<LocationModel>(), useCase.locations().first())
    }

    @Test
    fun `given location save, when limit not reached, then save allowed`() = runTest {

        //add locations
        repeat((1..3).count()) {
            useCase.save(location.copy(id = Random.nextDouble().toString()))
        }

        assertTrue(useCase.allowSave(limit = saveLimit))
    }

    @Test
    fun `given location save, when limit reached, then save not allowed`() = runTest {

        //add max locations
        repeat((1..saveLimit).count()) {
            useCase.save(location.copy(id = Random.nextInt().toString()))
        }

        assertFalse(useCase.allowSave(limit = saveLimit))
    }


}