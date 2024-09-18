package pk.mahdi.bookaven.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import pk.mahdi.core.ui.navigation.DestinationRoute
import pk.mahdi.core.ui.navigation.composable

val GRAPH_HOME_ROUTE = DestinationRoute("home_graph_route")
private const val ROUTE_HOME_SCREEN = "home"

fun NavGraphBuilder.homeGraph(
    onBookClick: (rootRoute: DestinationRoute, bookId: Int) -> Unit,
    nestedGraphs: NavGraphBuilder.(DestinationRoute) -> Unit
) {
    navigation(
        route = GRAPH_HOME_ROUTE.route,
        startDestination = "$GRAPH_HOME_ROUTE/$ROUTE_HOME_SCREEN"
    ) {
        composable(
            route = "$GRAPH_HOME_ROUTE/$ROUTE_HOME_SCREEN",
            screenName = "Home"
        ) {
            HomeScreen(
                onBookClick = { bookId ->
                    onBookClick(GRAPH_HOME_ROUTE, bookId)
                }
            )
        }

        nestedGraphs(GRAPH_HOME_ROUTE)
    }
}
