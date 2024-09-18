package pk.mahdi.bookaven.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import pk.mahdi.bookaven.model.bookservice.Book


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onBookClick: (Int) -> Unit // Pass book click event callback
) {
    val viewModel: HomeViewModel = hiltViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()
    var isSearchActive by remember { mutableStateOf(false) }


    Scaffold(
        topBar = {
            HomeTopAppBar(
                searchText = state.searchText,
                onSearchTextChanged = viewModel::onSearchTextChanged,
                onSearchTriggered = { viewModel.onSearchTextChanged(it) }, // Search triggered callback
                isSearchActive = isSearchActive,
                onActiveChange = { isSearchActive = it } // Handle search bar expansion/collapse
            )
        },
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            if (state.isLoading) {
                LoadingIndicator()
            } else if (state.error != null) {
                ErrorContent(
                    message = state.error!!,
                    onRetryClick = viewModel::onRetry
                )
            } else if (state.searchText.isBlank()) {
                BookList(
                    books = state.items,
                    onBookClick = onBookClick // Trigger the callback when a book is clicked
                )
            } else {
                SearchResults(
                    searchResults = state.searchResults,
                    onBookClick = onBookClick // Trigger the callback for search results
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopAppBar(
    searchText: String,
    onSearchTextChanged: (String) -> Unit,
    onSearchTriggered: (String) -> Unit, // Triggered when search is performed
    isSearchActive: Boolean,
    onActiveChange: (Boolean) -> Unit,   // Active state change callback
) {
    SearchBar(
        query = searchText,
        onQueryChange = onSearchTextChanged, // Called when the text changes
        onSearch = onSearchTriggered,        // Trigger the search action
        active = isSearchActive,
        onActiveChange = onActiveChange,     // Called when search bar is expanded/collapsed
        placeholder = { Text("Search Books") }, // Placeholder when searchText is empty
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search Icon") },
        trailingIcon = {
            if (searchText.isNotEmpty()) {
                IconButton(onClick = { onSearchTextChanged("") }) {
                    Icon(Icons.Default.Close, contentDescription = "Clear Search")
                }
            }
        },
        modifier = Modifier.fillMaxWidth()
    ) {
        // Optional: You can add content below the search bar (like search suggestions)
        // Here we leave this block empty.
    }
}


@Composable
fun BookList(books: List<Book>, onBookClick: (Int) -> Unit) {
    LazyColumn {
        items(books) { book ->
            BookCard(book = book, onClick = { onBookClick(book.id) }) // Pass book ID
        }
    }
}

@Composable
fun SearchResults(searchResults: List<Book>, onBookClick: (Int) -> Unit) {
    LazyColumn {
        items(searchResults) { book ->
            BookCard(book = book, onClick = { onBookClick(book.id) }) // Pass book ID
        }
    }
}

@Composable
fun BookCard(book: Book, onClick: () -> Unit) {
    Card(onClick = onClick, modifier = Modifier.padding(8.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(book.title, style = MaterialTheme.typography.bodyLarge)
            Text(book.authors.joinToString(), style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
fun LoadingIndicator() {
    CircularProgressIndicator(modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center))
}

@Composable
fun ErrorContent(message: String, onRetryClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = message)
        Button(onClick = onRetryClick) {
            Text("Retry")
        }
    }
}
