package ir.romina.porkar.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ir.romina.porkar.map.presentation.detail.StationDetailScreen
import ir.romina.porkar.map.presentation.home.StationsMapScreenRoute

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            StationsMapScreenRoute(
                modifier = Modifier,
                onStationClick = { stationId ->
                    navController.navigate("stationDetail/$stationId")
                }
            )
        }
        composable(
            route = "stationDetail/{stationId}",
            arguments = listOf(
                navArgument("stationId") {
                    type = NavType.StringType
                }
            )
        ) {
            val stationId = it.arguments?.getString("stationId")!!
            StationDetailScreen(stationId = stationId)
        }
    }
}
