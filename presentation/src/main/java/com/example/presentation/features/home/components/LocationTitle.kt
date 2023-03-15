package com.example.presentation.features.home.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.example.domain.model.LocationModel

@Composable
internal fun LocationTitle (location: LocationModel) {

    Text(
        modifier = Modifier.fillMaxWidth(),
        text = location.name,
        style = MaterialTheme.typography.h5,
        textAlign = TextAlign.Center
    )
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = location.region,
        style = MaterialTheme.typography.body2,
        textAlign = TextAlign.Center
    )

}