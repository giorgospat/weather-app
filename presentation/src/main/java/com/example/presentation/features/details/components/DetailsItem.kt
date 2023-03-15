package com.example.presentation.features.details.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.domain.model.DayForecast
import com.example.presentation.R
import com.example.presentation.features.home.celsius
import com.example.presentation.utils.DateFormat
import com.example.presentation.utils.formatDate
import kotlin.math.roundToInt

private val cardsPadding = 10.dp
private const val emptyValue = "-"

@Composable
internal fun DetailsItem(day: DayForecast) {

    Column(
        modifier = Modifier
            .padding(horizontal = cardsPadding)
            .fillMaxWidth()
    ) {

        val dayName = day.dateMillis?.formatDate(to = DateFormat.DAY_IN_WEEK_DAY_MONTH)

        //hide day is conversion fails
        if (dayName != null) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = dayName,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h6
            )
            Spacer(modifier = Modifier.height(10.dp))
        }

        Row {
            val minTemp = day.minTempCelsius?.celsius() ?: emptyValue
            DetailCard(
                title = stringResource(R.string.min_temp),
                value = minTemp
            )

            val maxTemp = day.maxTempCelsius?.celsius() ?: emptyValue
            DetailCard(title = stringResource(R.string.max_temp), value = maxTemp)
        }

        Row {
            val humidity = day.humidity?.let { hum -> "${hum.roundToInt()}%" } ?: emptyValue
            DetailCard(
                title = stringResource(R.string.humidity),
                value = humidity
            )

            val uv = day.uv?.toString() ?: emptyValue
            DetailCard(title = stringResource(R.string.uv_index), value = uv)
        }

        Row {
            val wind = day.windKm?.let { hum -> "$hum km/h" } ?: emptyValue
            DetailCard(title = stringResource(R.string.wind), value = wind)

            val visibility = day.visibilityKm?.let { vis -> "$vis km" } ?: emptyValue
            DetailCard(
                title = stringResource(R.string.visibility),
                value = visibility
            )
        }

        Row {
            val sunrise = day.sunrise ?: emptyValue
            DetailCard(title = stringResource(R.string.sunrise), value = sunrise)

            val sunset = day.sunset ?: emptyValue
            DetailCard(title = stringResource(R.string.sunset), value = sunset)
        }
    }

}