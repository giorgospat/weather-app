package com.example.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.domain.model.LatLng
import com.example.presentation.features.add_location.AddLocationScreen
import com.example.presentation.features.details.DetailsScreen
import com.example.presentation.features.home.HomeScreen

@Composable
fun NavigationComponent(navController: NavHostController, location: LatLng) {

    NavHost(
        navController = navController,
        startDestination = Screen.Home.toRoute()
    ) {
        composable(Screen.Home.toRoute()) {
            HomeScreen(
                viewModel = hiltViewModel(),
                location = location
            ) { screen, id ->
                if (screen == Screen.AddLocation) {
                    navController.navigate(Screen.AddLocation.toRoute())
                } else {
                    navController.navigate(Screen.Details.toRoute(id = id))
                }
            }
        }
        composable(
            route = Screen.Details.toPath(),
            arguments = listOf(navArgument(Arguments.id) { type = NavType.StringType })
        ) { backStackEntry ->
            val locationId = backStackEntry.arguments?.getString(Arguments.id)
            locationId?.let {
                DetailsScreen(viewModel = hiltViewModel()) {
                    navController.popBackStack()
                }
            }
        }
        composable(Screen.AddLocation.toRoute()) {
            AddLocationScreen(viewModel = hiltViewModel()) {
                navController.popBackStack()
            }
        }
    }

}