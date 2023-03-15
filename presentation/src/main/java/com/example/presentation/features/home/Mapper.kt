package com.example.presentation.features.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.res.stringResource
import com.example.presentation.R

@Composable
@ReadOnlyComposable
internal fun Double.celsius(): String {
    return "$this ${stringResource(id = R.string.celsius_symbol)}"
}