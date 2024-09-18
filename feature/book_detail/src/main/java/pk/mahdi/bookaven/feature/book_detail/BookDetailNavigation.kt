package pk.mahdi.bookaven.feature.book_detail

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.navArgument
import pk.mahdi.core.ui.navigation.DestinationRoute
import pk.mahdi.core.ui.navigation.composable

fun NavGraphBuilder.bookDetailScreen(
    rootRoute: DestinationRoute,
    onBack: () -> Unit,
) {
    composable(
        route = "${rootRoute.route}/bookDetail/{bookId}",
        screenName = "Book Detail",
        arguments = listOf(navArgument("bookId") { type = NavType.IntType })
    ) { backStackEntry ->
        val bookId = backStackEntry.arguments?.getInt("bookId") ?: 0
        BookDetailScreen(
            rootRoute = rootRoute,
            onBack = onBack
        )
    }
}

fun NavController.navigateToBookDetail(rootRoute: DestinationRoute, bookId: Int) {
    navigate("${rootRoute.route}/bookDetail/$bookId")
}