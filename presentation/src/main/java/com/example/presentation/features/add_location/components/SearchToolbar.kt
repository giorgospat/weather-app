package com.example.presentation.features.add_location.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.presentation.R

@Composable
internal fun SearchToolbar(
    value: String,
    onValueChange: (String) -> Unit,
    onBack: () -> Unit
) {

    Column(modifier = Modifier.fillMaxWidth()) {

        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterStart) {

            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.back),
                    tint = MaterialTheme.colors.onBackground
                )
            }

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.add_location),
                style = MaterialTheme.typography.h5,
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            modifier = Modifier.padding(horizontal = 20.dp).fillMaxWidth(),
            value = value,
            onValueChange = onValueChange,
            label = {
                Text(text = stringResource(id = R.string.search_location_label))
            }
        )

    }

}