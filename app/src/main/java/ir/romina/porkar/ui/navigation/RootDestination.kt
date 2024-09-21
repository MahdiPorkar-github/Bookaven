package ir.romina.porkar.ui.navigation

import ir.romina.porkar.R

enum class RootDestination(
  val rootScreen: RootScreen,
  val selectedIcon: Int,
  val title: String,
) {
  Home(
    rootScreen = RootScreen.Home,
    selectedIcon = R.drawable.ic_nav_home,
    title = "Home",
  ),
  Categories(
    rootScreen = RootScreen.Categories,
    selectedIcon = R.drawable.ic_nav_categories,
    title = "Categories",
  ),
  Library(
    rootScreen = RootScreen.Library,
    selectedIcon = R.drawable.ic_nav_library,
    title = "Library",
  ),

  Settings(
    rootScreen = RootScreen.Settings,
    selectedIcon = R.drawable.ic_nav_settings,
    title = "settings",
  ),
}
