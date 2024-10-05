package pk.mahdi.bookaven

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import pk.mahdi.bookaven.designsystem.theme.MahdiPkTheme
import pk.mahdi.bookaven.ui.BookavenApp


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