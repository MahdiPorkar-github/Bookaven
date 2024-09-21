package ir.romina.porkar.ui

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navOptions
import ir.romina.porkar.ui.navigation.BookAppNavHost
import ir.romina.porkar.ui.navigation.BookavenNavigationBar
import ir.romina.porkar.ui.navigation.RootDestination
import ir.romina.porkar.ui.navigation.RootScreen
import ir.romina.porkar.ui.navigation.RootScreen.*

@Composable
fun BookavenApp(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        bottomBar = {
            BookavenNavigationBar(
                destinations = RootDestination.entries,
                currentDestination = navController.currentBackStackEntryAsState().value?.destination,
                onNavigationSelected = { destination ->
                    val topLevelNavOptions = navOptions {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }

                    when (destination) {
                        Home -> navController.navigate(Home.destinationRoute.route, topLevelNavOptions)
                        Categories -> navController.navigate(Categories.destinationRoute.route, topLevelNavOptions)
                        Library -> navController.navigate(Library.destinationRoute.route, topLevelNavOptions)
                        Settings -> navController.navigate(Settings.destinationRoute.route, topLevelNavOptions)
                    }
                },
            )
        },
        contentWindowInsets = WindowInsets(0, 0, 0, 0)
    ) { padding ->
        BookAppNavHost(
            navController = navController,
            modifier = Modifier
                .padding(padding)
                .consumeWindowInsets(padding)
        )
    }
}
