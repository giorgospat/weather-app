package com.example.presentation.features.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.domain.model.CurrentWeather
import com.example.presentation.components.LoadImage
import com.example.presentation.utils.DateFormat
import com.example.presentation.utils.formatDate

@Composable
internal fun CurrentWeather(weather: CurrentWeather) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        LoadImage(
            modifier = Modifier.size(100.dp),
            url = weather.condition.icon
        )

        Spacer(modifier = Modifier.height(5.dp))

        //hide text is temperature is null
        if (weather.tempCelsius != null) {
            Text(text = weather.tempCelsius.toString(), style = MaterialTheme.typography.h3)
        }

        Spacer(modifier = Modifier.height(5.dp))

        Text(text = weather.condition.text, style = MaterialTheme.typography.h4)

        Spacer(modifier = Modifier.height(10.dp))

        val lastUpdated =
            weather.lastUpdatedMillis?.formatDate(to = DateFormat.DAY_IN_WEEK_MONTH_TIME)

        //hide date is conversion fails
        if (lastUpdated != null) {
            Text(text = lastUpdated, style = MaterialTheme.typography.body2)
        }

    }

}