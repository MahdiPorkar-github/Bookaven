package pk.mahdi.bookaven.feature.categories

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import pk.mahdi.core.ui.navigation.DestinationRoute
import pk.mahdi.core.ui.navigation.composable


val GRAPH_CATEGORIES_ROUTE = DestinationRoute("categories_graph_route")
private const val ROUTE_CATEGORIES_SCREEN = "categories"


fun NavGraphBuilder.categoriesGraph(
    onCategoryClick: (rootRoute: DestinationRoute) -> Unit,
    nestedGraphs: NavGraphBuilder.(DestinationRoute) -> Unit
) {
    navigation(
        route = GRAPH_CATEGORIES_ROUTE.route,
        startDestination = "$GRAPH_CATEGORIES_ROUTE/$ROUTE_CATEGORIES_SCREEN"
    ) {
        composable(
            route = "$GRAPH_CATEGORIES_ROUTE/$ROUTE_CATEGORIES_SCREEN",
            screenName = "Home"
        ) {
            CategoriesScreen()
        }

        nestedGraphs(GRAPH_CATEGORIES_ROUTE)
    }
}

