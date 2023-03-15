package com.example.common


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.mockito.MockitoAnnotations.openMocks

@OptIn(ExperimentalCoroutinesApi::class)
abstract class KotlinTest {

    @Before
    open fun before() {
        openMocks(this)
        Dispatchers.setMain(Dispatchers.Unconfined)
        init()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    abstract fun init()
}