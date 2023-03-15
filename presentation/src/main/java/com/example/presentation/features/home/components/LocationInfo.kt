package com.example.presentation.features.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.unit.dp
import com.example.domain.model.ForecastModel

@Composable
internal fun LocationInfo(weather: ForecastModel, onLocationClick: (String) -> Unit) {

    LazyColumn(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .fillMaxWidth()
            .clearAndSetSemantics { contentDescription = weather.location.name }
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                onLocationClick(weather.location.id)
            }
    ) {

        item {
            LocationTitle(location = weather.location)
        }

        item {
            CurrentWeather(weather = weather.current)
        }

        item {
            Spacer(modifier = Modifier.height(50.dp))
        }

        items(items = weather.forecast) { forecast ->
            DayForecast(forecast = forecast)
            Spacer(modifier = Modifier.height(10.dp))
        }

    }

}