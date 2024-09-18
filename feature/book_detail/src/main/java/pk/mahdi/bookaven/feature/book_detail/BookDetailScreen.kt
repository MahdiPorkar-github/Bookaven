package pk.mahdi.bookaven.feature.book_detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import pk.mahdi.core.ui.navigation.DestinationRoute

@Composable
fun BookDetailScreen(
    rootRoute: DestinationRoute,
    onBack: () -> Unit,
) {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Text(text = "Detail Screen")

    }
}