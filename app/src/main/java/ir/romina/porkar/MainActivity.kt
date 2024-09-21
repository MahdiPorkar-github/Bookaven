package ir.romina.porkar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ir.romina.porkar.designsystem.theme.MahdiPkTheme
import ir.romina.porkar.ui.BookavenApp


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            MahdiPkTheme {
                BookavenApp(navController = navController)
            }

        }
    }
}