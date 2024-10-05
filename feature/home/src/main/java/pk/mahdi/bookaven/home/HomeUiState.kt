package pk.mahdi.bookaven.home

import pk.mahdi.bookaven.model.bookservice.Book

data class AllBooksUiState(
    val isLoading: Boolean,
    val items: List<Book> = emptyList(),
    val error: String? = null,
    val endReached: Boolean = false,
    val page: Long = 1L
)