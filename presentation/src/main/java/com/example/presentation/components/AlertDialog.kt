package com.example.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties

@Composable
fun AlertDialog(
    title: String,
    text: String,
    buttonText: String,
    onClick: () -> Unit
) {

    androidx.compose.material.AlertDialog(
        modifier = Modifier.padding(horizontal = 20.dp),
        onDismissRequest = onClick,
        shape = RoundedCornerShape(size = 10.dp),
        title = { Text(text = title) },
        text = { Text(text = text) },
        confirmButton = {
            Button(onClick = onClick) {
                Text(text = buttonText)
            }
        },
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        )
    )

}