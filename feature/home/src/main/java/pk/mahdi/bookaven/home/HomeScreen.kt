package pk.mahdi.bookaven.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import pk.mahdi.bookaven.components.BookItemCard
import pk.mahdi.bookaven.components.ProgressDots
import pk.mahdi.bookaven.home.utils.BookUtils
import pk.mahdi.bookaven.model.bookservice.Book


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onBookClick: (Int) -> Unit
) {
    val viewModel: HomeViewModel = hiltViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = true) {
        delay(200)
        viewModel.loadNextItems()
    }

    Scaffold(
        topBar = {

        },
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            if (state.error != null) {
                ErrorContent(
                    message = state.error!!,
                    onRetryClick = { viewModel.reloadItems() }
                )
            } else {
                AllBooksList(
                    state = state,
                    onBookClick = onBookClick,
                    onRetryClicked = { },
                    onLoadNextItems = { viewModel.loadNextItems() }
                )
            }
        }
    }
}


@Composable
private fun AllBooksList(
    state: AllBooksUiState,
    onBookClick: (Int) -> Unit,
    onRetryClicked: () -> Unit,
    onLoadNextItems: () -> Unit
) {


    AnimatedVisibility(
        visible = !state.isLoading || state.error == null,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(start = 8.dp, end = 8.dp),
            columns = GridCells.Adaptive(295.dp)
        ) {
            items(state.items.size) { i ->
                val item = state.items[i]
                if (i >= state.items.size - 1
                    && !state.endReached
                    && !state.isLoading
                ) {
                    onLoadNextItems()
                }
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    BookItemCard(
                        title = item.title,
                        author = BookUtils.getAuthorsAsString(item.authors),
                        language = BookUtils.getLanguagesAsString(item.languages),
                        subjects = BookUtils.getSubjectsAsString(
                            item.subjects, 3
                        ),
                        coverImageUrl = item.formats.imageJpeg
                    ) {
                        onBookClick(item.id)
                    }
                }
            }

            item {
                AnimatedVisibility(
                    visible = state.isLoading,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        ProgressDots()
                    }
                }
            }
        }
    }
}


@Composable
fun ErrorContent(message: String, onRetryClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(48.dp)
        ,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = message)
        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = onRetryClick) {
            Text("Retry")
        }
    }
}
