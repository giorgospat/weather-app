package com.example.presentation.features.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.domain.model.DayForecast
import com.example.presentation.components.LoadImage
import com.example.presentation.features.home.celsius
import com.example.presentation.utils.DateFormat
import com.example.presentation.utils.formatDate

@Composable
internal fun DayForecast(forecast: DayForecast) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {

        val day = forecast.dateMillis?.formatDate(to = DateFormat.DAY_IN_WEEK)

        //hide day is conversion fails
        if (day != null) {
            Text(
                modifier = Modifier.weight(1f),
                text = day,
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.body1
            )
        }

        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            LoadImage(
                modifier = Modifier.size(30.dp),
                url = forecast.condition.icon
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = forecast.condition.text,
                style = MaterialTheme.typography.body1
            )
        }

        Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.CenterEnd) {
            //hide text is temperature is null
            forecast.averageTempCelsius?.let { maxTemp ->
                Text(
                    text = maxTemp.celsius(),
                    style = MaterialTheme.typography.body1
                )
            }
        }

    }

}