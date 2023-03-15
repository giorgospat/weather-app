package com.example.presentation.snackbar

sealed class SnackbarState {

    data class Info(val message: String) : SnackbarState()

    object None : SnackbarState()

}