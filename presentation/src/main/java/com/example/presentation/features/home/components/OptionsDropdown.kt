package com.example.presentation.features.home.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.presentation.R
import com.example.presentation.features.home.model.OptionType

@Composable
internal fun OptionsDropdown(removeEnabled: Boolean, onClick: (OptionType) -> Unit) {

    var expanded by remember { mutableStateOf(false) }

    Box(contentAlignment = Alignment.Center) {

        IconButton(onClick = { expanded = true }) {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = stringResource(R.string.options_description),
                tint = Color.White,
                modifier = Modifier.size(36.dp)
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {

            DropdownMenuItem(
                onClick = {
                    onClick(OptionType.Add)
                    expanded = false
                },
            ) {
                Text(text = stringResource(R.string.add_location))
            }

            if (removeEnabled) {
                DropdownMenuItem(
                    onClick = {
                        onClick(OptionType.Delete)
                        expanded = false
                    },
                ) {
                    Text(text = stringResource(R.string.delete_location))
                }
            }
        }
    }

}