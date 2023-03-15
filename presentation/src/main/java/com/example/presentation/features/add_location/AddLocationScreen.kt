package com.example.presentation.features.add_location

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.domain.model.LocationModel
import com.example.domain.model.toText
import com.example.presentation.R
import com.example.presentation.features.add_location.components.SearchToolbar
import com.example.presentation.features.add_location.model.AddLocationMessage
import com.example.presentation.snackbar.SnackbarState

@Composable
internal fun AddLocationScreen(viewModel: AddLocationViewModel, onBack: () -> Unit) {

    val searchTerm by viewModel.searchTerm.collectAsStateWithLifecycle()
    val searchResults by viewModel.searchResults.collectAsStateWithLifecycle()
    val context = LocalContext.current

    /** handle snackbar messages */
    LaunchedEffect(Unit) {
        viewModel.infoMessage.collect { info ->
            when (info) {
                AddLocationMessage.Save -> {
                    viewModel.snackBar.show(
                        snackbar = SnackbarState.Info(
                            message = context.getString(R.string.location_added)
                        )
                    )
                    onBack() //navigate to home screen after location click
                }
                AddLocationMessage.ResultError -> {
                    viewModel.snackBar.show(
                        snackbar = SnackbarState.Info(
                            message = context.getString(R.string.generic_error)
                        )
                    )
                }
            }

        }
    }

    Content(
        searchTerm = searchTerm,
        searchResults = searchResults,
        onSearchChange = viewModel::onSearchChange,
        onSave = viewModel::onClickSave,
        onBack = onBack
    )

}

@Composable
private fun Content(
    searchTerm: String,
    searchResults: List<LocationModel>,
    onSearchChange: (String) -> Unit,
    onSave: (LocationModel) -> Unit,
    onBack: () -> Unit
) {

    Column(
        modifier = Modifier
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        MaterialTheme.colors.primary.copy(alpha = 0.2f),
                        MaterialTheme.colors.surface
                    )
                )
            )
            .padding(vertical = 30.dp)
            .navigationBarsPadding()
            .fillMaxSize()
    ) {

        SearchToolbar(
            value = searchTerm,
            onValueChange = onSearchChange,
            onBack = onBack
        )

        LazyColumn(modifier = Modifier.padding(horizontal = 20.dp)) {
            items(items = searchResults) { result ->

                TextButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { onSave(result) }
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = result.toText(),
                        textAlign = TextAlign.Start,
                        style = MaterialTheme.typography.body1
                    )
                }
            }
        }

    }

}