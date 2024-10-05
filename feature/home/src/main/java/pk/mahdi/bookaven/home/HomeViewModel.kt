package pk.mahdi.bookaven.home

import Paginator
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
import pk.mahdi.bookaven.model.bookservice.Book
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
            try {
                bookRepository.getAllBooks(nextPage)
            } catch (exc: Exception) {
                Result.failure(exc)
            }
        },
        getNextPage = {
            _state.value.page + 1L
        },
        onError = { throwable ->
            _state.update {
                it.copy(error = throwable?.localizedMessage ?: "UNKNOWN_ERROR")
            }
        },
        onSuccess = { bookSet, newPage ->
            val books = if (bookSet.books != null) {
                val books =
                    bookSet.books.filter { it.formats.applicationEpubZip != null } as ArrayList<Book>
                // Remove the book with id 1513
                val index = books.indexOfFirst { it.id == 1513 }
                if (index != -1) {
                    books.removeAt(index)
                }
                books // return the list of books
            } else {
                ArrayList()
            }

            _state.update {
                it.copy(
                    items = (_state.value.items + books),
                    page = newPage,
                    endReached = books.isEmpty()
                )
            }
        }
    )

    fun loadNextItems() {
        viewModelScope.launch {
            pagination.loadNextItems()
        }
    }


    fun reloadItems() {
        pagination.reset()
        _state.update {
            AllBooksUiState(isLoading = false)
        }
        loadNextItems()
    }
}

