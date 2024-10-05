package pk.mahdi.bookaven.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import pk.mahdi.bookaven.feature.book_detail.navigateToBookDetail
import pk.mahdi.bookaven.home.GRAPH_HOME_ROUTE
import pk.mahdi.bookaven.home.homeGraph
import pk.mahdi.bookaven.feature.book_detail.bookDetailScreen
import pk.mahdi.bookaven.feature.categories.categoriesGraph
import pk.mahdi.core.ui.navigation.DestinationRoute
import pk.mahdi.library.libraryGraph
import pk.mahdi.settings.settingsGraph

@Composable
fun BookAppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = GRAPH_HOME_ROUTE.route
    ) {
        homeGraph(
            onBookClick = { rootRoute, bookId ->
                navController.navigateToBookDetail(rootRoute, bookId)
            },
            nestedGraphs = { rootRoute ->
                addBookDetailScreen(rootRoute, navController)
            }
        )

        categoriesGraph(
            onCategoryClick = { rootRoute ->

            },
            nestedGraphs = {

            }
        )

        libraryGraph(
            nestedGraphs = {

            }
        )


        settingsGraph(

            nestedGraphs = {

            }
        )

    }
}

private fun NavGraphBuilder.addBookDetailScreen(
    rootRoute: DestinationRoute,
    navController: NavHostController,
) {
    bookDetailScreen(
        rootRoute = rootRoute,
        onBack = navController::popBackStack,
    )
}