package pk.mahdi.library

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import pk.mahdi.core.ui.navigation.DestinationRoute
import pk.mahdi.core.ui.navigation.composable


val GRAPH_LIBRARY_ROUTE = DestinationRoute("library_graph_route")
private const val ROUTE_LIBRARY_SCREEN = "library"


fun NavGraphBuilder.libraryGraph(
    nestedGraphs: NavGraphBuilder.(DestinationRoute) -> Unit
) {
    navigation(
        route = GRAPH_LIBRARY_ROUTE.route,
        startDestination = "$GRAPH_LIBRARY_ROUTE/$ROUTE_LIBRARY_SCREEN"
    ) {
        composable(
            route = "$GRAPH_LIBRARY_ROUTE/$ROUTE_LIBRARY_SCREEN",
            screenName = "library"
        ) {
            LibraryScreen()
        }

        nestedGraphs(GRAPH_LIBRARY_ROUTE)
    }
}

