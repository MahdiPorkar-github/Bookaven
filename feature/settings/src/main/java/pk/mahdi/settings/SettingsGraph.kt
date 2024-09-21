package pk.mahdi.settings

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import pk.mahdi.core.ui.navigation.DestinationRoute
import pk.mahdi.core.ui.navigation.composable


val GRAPH_SETTINGS_ROUTE = DestinationRoute("settings_graph_route")
private const val ROUTE_SETTINGS_SCREEN = "settings"


fun NavGraphBuilder.settingsGraph(
    nestedGraphs: NavGraphBuilder.(DestinationRoute) -> Unit
) {
    navigation(
        route = GRAPH_SETTINGS_ROUTE.route,
        startDestination = "$GRAPH_SETTINGS_ROUTE/$ROUTE_SETTINGS_SCREEN"
    ) {
        composable(
            route = "$GRAPH_SETTINGS_ROUTE/$ROUTE_SETTINGS_SCREEN",
            screenName = "Settings"
        ) {
            SettingsScreen()
        }

        nestedGraphs(GRAPH_SETTINGS_ROUTE)
    }
}

