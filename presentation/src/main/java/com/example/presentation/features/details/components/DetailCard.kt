package com.example.presentation.features.details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
internal fun RowScope.DetailCard(title: String, value: String) {

    Column(
        modifier = Modifier
            .weight(1f)
            .padding(10.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colors.onPrimary.copy(alpha = 0.7f)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column(modifier = Modifier.padding(vertical = 10.dp)) {
            Text(
                text = title,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.subtitle2
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = value,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.body1
            )
        }
    }

}