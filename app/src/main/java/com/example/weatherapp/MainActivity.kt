package com.example.weatherapp

import android.annotation.SuppressLint
import android.location.Location
import android.os.Bundle
import android.os.Looper
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.example.presentation.components.AlertDialog
import com.example.presentation.navigation.NavigationComponent
import com.example.presentation.snackbar.SnackBar
import com.example.presentation.snackbar.SnackbarState
import com.example.presentation.theme.WeatherAppTheme
import com.example.weatherapp.utils.locationPermission
import com.example.weatherapp.utils.locationRequest
import com.google.accompanist.permissions.*
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var snackBar: SnackBar
    private val viewModel: MainViewModel by viewModels()
    private lateinit var fusedLocationClient: FusedLocationProviderClient


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        setContent {

            val navController = rememberNavController()
            val snackbarState = remember { SnackbarHostState() }
            val location by viewModel.location.collectAsStateWithLifecycle()

            val scope = rememberCoroutineScope()

            LaunchedEffect(Unit) {
                snackBar.snackbar.collect { state ->
                    if (state is SnackbarState.Info) {
                        scope.coroutineContext.cancelChildren() //clear previous messages
                        scope.launch {
                            snackbarState.showSnackbar(message = state.message)
                        }
                    }
                }
            }

            WeatherAppTheme {
                Scaffold(
                    modifier = Modifier
                        .navigationBarsPadding()
                        .fillMaxSize(),
                    snackbarHost = {
                        SnackbarHost(
                            modifier = Modifier.padding(bottom = 20.dp),
                            hostState = snackbarState
                        )
                    }
                ) { padding ->
                    Box(modifier = Modifier.padding(padding)) {
                        NavigationComponent(navController = navController, location = location)
                    }
                }
            }

            RequestLocation()

        }
    }

    /**
     * Location request implementation
     */

    @OptIn(ExperimentalPermissionsApi::class)
    @Composable
    private fun RequestLocation() {

        var showPermissionInfo by remember { mutableStateOf(true) }
        val locationState = rememberPermissionState(locationPermission)

        if (showPermissionInfo) {
            AlertDialog(
                title = stringResource(id = R.string.location_permission_title),
                text = stringResource(id = R.string.location_permission_message),
                buttonText = stringResource(id = R.string.ok),
                onClick = {
                    showPermissionInfo = false
                    locationState.launchPermissionRequest()
                }
            )
        }


        // request location permission if not granted
        if (!locationState.status.isGranted && !showPermissionInfo) {
            LaunchedEffect(locationState) {
                locationState.launchPermissionRequest()
            }
        }

        //location permissions actions
        if (locationState.status.isGranted) {
            showPermissionInfo = false
            LaunchedEffect(locationState) {
                requestFusedLocation()
            }
        }

    }

    @SuppressLint("MissingPermission")
    private fun requestFusedLocation() {
        fusedLocationClient.lastLocation.addOnSuccessListener { locationResult: Location? ->
            locationResult?.let { location ->
                Log.d("LastLocation", "last $location")
                stopLocationUpdates() //stop updates after fetching location once
                viewModel.updateLocation(location)
            }
        }

        fusedLocationClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.getMainLooper()
        )

    }

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(result: LocationResult) {
            super.onLocationResult(result)
            result.lastLocation?.let { location ->
                Log.d("LastLocation", "updated: $location")
                stopLocationUpdates() //stop updates after fetching location once
                viewModel.updateLocation(location)
            }
        }
    }

    override fun onPause() {
        super.onPause()
        stopLocationUpdates()
    }

    private fun stopLocationUpdates() {
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

}