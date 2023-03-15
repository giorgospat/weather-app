package com.example.presentation

import com.example.presentation.snackbar.SnackBar
import com.example.presentation.snackbar.SnackbarState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SnackBarTest {

    private val snackBar = SnackBar()

    @Test
    fun `given snackBar, when message, then it is displayed`() = runTest {
        val message = SnackbarState.Info("some message")
        snackBar.show(message)

        assertEquals(message, snackBar.snackbar.first())
    }

    @Test
    fun `given snackBar, when multiple messages, then all displayed`() = runTest {

        snackBar.show(SnackbarState.Info("message 1"))
        assertEquals(SnackbarState.Info("message 1"), snackBar.snackbar.first())

        snackBar.show(SnackbarState.Info("message 2"))
        assertEquals(SnackbarState.Info("message 2"), snackBar.snackbar.first())

        snackBar.show(SnackbarState.Info("message 3"))
        assertEquals(SnackbarState.Info("message 3"), snackBar.snackbar.first())
    }

}