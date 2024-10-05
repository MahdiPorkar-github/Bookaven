package pk.mahdi.bookaven.home

import pk.mahdi.bookaven.Paginator
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pk.mahdi.bookaven.DataError
import pk.mahdi.bookaven.data.repository.BookRepository
import pk.mahdi.bookaven.model.bookservice.Book
import pk.mahdi.bookaven.model.bookservice.BookSet
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val bookRepository: BookRepository
) : ViewModel() {

    private val _state = MutableStateFlow(AllBooksUiState(isLoading = false))
    val state = _state.asStateFlow()

    private val pagination = Paginator(
        initialPage = state.value.page,
        onLoadUpdated = { isLoading ->
            _state.update {
                it.copy(isLoading = isLoading)
            }
        },
        onRequest = { nextPage ->
            bookRepository.getAllBooks(nextPage)
        },
        getNextPage = { bookSet ->
            _state.value.page + 1L
        },
        onError = { error ->
            val errorMessage = mapDataErrorToMessage(error)
            _state.update {
                it.copy(error = errorMessage)
            }
        },
        onSuccess = { bookSet, newPage ->
            val books = bookSet.books.filter { it.formats.applicationEpubZip != null }
            _state.update {
                it.copy(
                    items = _state.value.items + books,
                    page = newPage,
                    endReached = books.isEmpty(),
                    error = null
                )
            }
        }
    )


    /**
     * Initiates loading of the next set of items.
     */
    fun loadNextItems() {
        viewModelScope.launch {
            pagination.loadNextItems()
        }
    }

    /**
     * Reloads items by resetting the paginator and state.
     */
    fun reloadItems() {
        pagination.reset()
        _state.update {
            AllBooksUiState(
                isLoading = false,
                items = emptyList(),
                error = null,
                endReached = false,
                page = 1L
            )
        }
        loadNextItems()
    }

    /**
     * Maps DataError.Network to a user-friendly error message.
     *
     * @param error The DataError.Network to map.
     * @return A string message representing the error.
     */
    private fun mapDataErrorToMessage(error: DataError.Network): String {
        return when (error) {
            DataError.Network.NO_INTERNET -> "No internet connection."
            DataError.Network.REQUEST_TIMEOUT -> "Request timed out."
            DataError.Network.TOO_MANY_REQUESTS -> "Too many requests. Please try again later."
            DataError.Network.PAYLOAD_TOO_LARGE -> "Payload too large."
            DataError.Network.SERVER_ERROR -> "Server error occurred."
            DataError.Network.SERIALIZATION -> "Data serialization error."
            DataError.Network.UNKNOWN -> "An unknown error occurred."
        }
    }
}


