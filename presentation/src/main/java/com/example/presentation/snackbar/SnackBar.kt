package com.example.presentation.snackbar

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

class SnackBar @Inject constructor() {

    private val _snackbar = Channel<SnackbarState>(Channel.BUFFERED)
    val snackbar = _snackbar.receiveAsFlow()

    fun show(snackbar: SnackbarState) {
        _snackbar.trySend(snackbar)
    }


}