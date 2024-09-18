package pk.mahdi.bookaven.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pk.mahdi.bookaven.data.repository.BookRepository
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val bookRepository: BookRepository // Injecting the repository
) : ViewModel() {

    private val _state = MutableStateFlow(AllBooksUiState(isLoading = false))
    val state = _state.asStateFlow()

    init {
        loadBooks() // Loading books initially
    }

    private fun loadBooks() {
        viewModelScope.launch {
            loadNextBooksPage()
        }
    }

    // Function to handle search input changes
    fun onSearchTextChanged(query: String,) {
        _state.update { state ->
            state.copy(searchText = query, isSearching = true)
        }
         viewModelScope.launch {
                delay(500L) // Debounce to avoid excessive API calls
                searchBooks(query)

        }
    }

    // Function to load the next page of books using the repository
    private suspend fun loadNextBooksPage() {
        _state.update { state -> state.copy(isLoading = true) }

        // Fetching paginated books using the repository and handling success/failure
        bookRepository.getAllBooks(_state.value.page)
            .onEach { result ->
                result.fold(
                    onSuccess = { bookSet ->
                        val filteredBooks = bookSet.books.filter { it.formats.applicationEpubZip != null }
                        _state.update { state ->
                            state.copy(
                                items = state.items + filteredBooks,
                                isLoading = false,
                                endReached = filteredBooks.isEmpty(),
                                page = state.page + 1
                            )
                        }
                    },
                    onFailure = { throwable ->
                        _state.update { state ->
                            state.copy(error = throwable.localizedMessage ?: "Unknown Error", isLoading = false)
                        }
                    }
                )
            }
            .launchIn(viewModelScope) // Launch Flow in the ViewModel's coroutine scope
    }

    // Function to search for books based on user input
    private suspend fun searchBooks(query: String) {
        if (query.isBlank()) return

        // Searching books using the repository
        bookRepository.searchBooks(query)
            .onEach { result ->
                result.fold(
                    onSuccess = { bookSet ->
                        val books = bookSet.books.filter { it.formats.applicationEpubZip != null }
                        _state.update { state -> state.copy(searchResults = books, isSearching = false) }
                    },
                    onFailure = { throwable ->
                        _state.update { state -> state.copy(error = throwable.localizedMessage ?: "Search Failed", isSearching = false) }
                    }
                )
            }
            .launchIn(viewModelScope)
    }

    // Reload books (used in error cases)
    fun reloadBooks() {
        _state.update { state ->
            state.copy(page = 1, items = emptyList(), searchResults = emptyList(), isLoading = true)
        }
        loadBooks()
    }

    // Retry logic for error cases
    fun onRetry() {
        reloadBooks()
    }
}

