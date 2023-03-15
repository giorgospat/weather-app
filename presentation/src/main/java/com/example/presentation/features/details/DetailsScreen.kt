package com.example.presentation.features.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.presentation.features.details.components.DetailsItem
import com.example.presentation.features.details.components.DetailsToolbar
import com.example.presentation.features.home.components.CurrentWeather


@Composable
internal fun DetailsScreen(viewModel: DetailsViewModel, onBack: () -> Unit) {

    val forecast by viewModel.forecast.collectAsStateWithLifecycle()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.primary.copy(alpha = 0.5f))
    ) {

        item {
            Spacer(modifier = Modifier.height(30.dp))

            DetailsToolbar(title = forecast.location.name, onBack = onBack)

            CurrentWeather(weather = forecast.current)

            Spacer(modifier = Modifier.height(20.dp))

        }

        items(forecast.forecast) { day ->
            DetailsItem(day = day)
            Spacer(modifier = Modifier.height(10.dp))
        }

    }

}