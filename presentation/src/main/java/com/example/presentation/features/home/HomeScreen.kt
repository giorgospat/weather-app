package com.example.presentation.features.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.domain.model.ForecastModel
import com.example.domain.model.LatLng
import com.example.domain.model.LocationModel
import com.example.presentation.R
import com.example.presentation.features.home.components.LocationInfo
import com.example.presentation.features.home.components.OptionsDropdown
import com.example.presentation.features.home.model.InfoMessage
import com.example.presentation.features.home.model.OptionType
import com.example.presentation.navigation.Screen
import com.example.presentation.snackbar.SnackbarState
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import java.util.*

@Composable
internal fun HomeScreen(
    viewModel: HomeViewModel,
    location: LatLng,
    navigate: (screen: Screen, id: String) -> Unit
) {

    val forecasts by viewModel.allForecasts.collectAsStateWithLifecycle()
    val loading by viewModel.loading.collectAsStateWithLifecycle()

    val context = LocalContext.current

    /** observe location update and fetch initial data */
    LaunchedEffect(location) {
        viewModel.refresh(location)
    }

    /** handle snackbar messages */
    LaunchedEffect(Unit) {
        viewModel.infoMessage.collect { info ->
            val message = when (info) {
                InfoMessage.Delete -> context.getString(R.string.location_deleted)
                InfoMessage.Save -> context.getString(R.string.location_added)
                InfoMessage.SaveNotAvailable -> context.getString(R.string.save_not_available)
                InfoMessage.LocationError -> context.getString(R.string.generic_error)
            }
            viewModel.snackBar.show(SnackbarState.Info(message = message))
        }
    }

    /** handle navigation */
    LaunchedEffect(Unit) {
        viewModel.navigate.collect { (screen, id) ->
            navigate(screen, id)
        }
    }

    /** display content */
    Content(
        loading = loading,
        forecasts = forecasts,
        onClickOptions = viewModel::onClickOption,
        onLocationClick = viewModel::onLocationClick
    )

}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun Content(
    loading: Boolean,
    forecasts: List<ForecastModel>,
    onClickOptions: (type: OptionType, name: LocationModel) -> Unit,
    onLocationClick: (String) -> Unit
) {

    val pagerState = rememberPagerState()

    Box(
        modifier = Modifier
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        MaterialTheme.colors.primary.copy(alpha = 0.7f),
                        MaterialTheme.colors.surface
                    )
                )
            )
            .navigationBarsPadding()
            .fillMaxSize()

    ) {

        if (loading) {

            CircularProgressIndicator(
                modifier = Modifier
                    .size(100.dp)
                    .align(Alignment.Center)
            )

        } else {

            HorizontalPager(
                modifier = Modifier.padding(vertical = 30.dp),
                state = pagerState,
                count = forecasts.size
            ) { page: Int ->
                LocationInfo(weather = forecasts[page], onLocationClick = onLocationClick)
            }

            HorizontalPagerIndicator(
                pagerState = pagerState,
                activeColor = MaterialTheme.colors.primary,
                modifier = Modifier
                    .padding(bottom = 10.dp)
                    .align(alignment = Alignment.BottomCenter),
            )

            if (forecasts.isNotEmpty()) {
                Row(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(top = 50.dp, end = 10.dp)
                ) {

                    OptionsDropdown(
                        //hide remove option in primary location screen
                        removeEnabled = forecasts.getOrNull(pagerState.currentPage)?.primary != true,
                        onClick = { type ->
                            onClickOptions(
                                type,
                                forecasts.getOrNull(pagerState.currentPage)?.location
                                    ?: LocationModel.Empty
                            )
                        }
                    )
                }
            }
        }

    }

}