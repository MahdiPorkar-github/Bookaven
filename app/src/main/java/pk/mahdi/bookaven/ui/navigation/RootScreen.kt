package pk.mahdi.bookaven.ui.navigation

import pk.mahdi.core.ui.navigation.DestinationRoute
import pk.mahdi.bookaven.feature.categories.GRAPH_CATEGORIES_ROUTE
import pk.mahdi.bookaven.home.GRAPH_HOME_ROUTE
import pk.mahdi.library.GRAPH_LIBRARY_ROUTE
import pk.mahdi.settings.GRAPH_SETTINGS_ROUTE

sealed class RootScreen(val destinationRoute: DestinationRoute) {
  data object Home : RootScreen(GRAPH_HOME_ROUTE)
  data object Categories : RootScreen(GRAPH_CATEGORIES_ROUTE)
  data object Library : RootScreen(GRAPH_LIBRARY_ROUTE)
  data object Settings : RootScreen(GRAPH_SETTINGS_ROUTE)
}
