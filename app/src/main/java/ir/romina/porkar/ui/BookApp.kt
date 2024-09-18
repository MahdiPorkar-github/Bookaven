package ir.romina.porkar.ui

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import ir.romina.porkar.ui.navigation.BookAppNavHost

@Composable
fun BookApp(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Scaffold(
        bottomBar = {
            // You can add a BottomNavigationBar here
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
