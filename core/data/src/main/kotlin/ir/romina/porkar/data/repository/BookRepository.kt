// repository/BookRepository.kt

package ir.romina.porkar.data.repository

import ir.romina.porkar.network.model.response.BookSet
import kotlinx.coroutines.flow.Flow

interface BookRepository {
    /**
     * Retrieves all books with pagination and optional language filtering.
     *
     * @param page The page number for pagination.
     * @param language Optional language filter.
     * @return A Flow emitting Result with BookSet data or an error.
     */
    fun getAllBooks(
        page: Long,
        language: String? = null
    ): Flow<Result<BookSet>>

    /**
     * Searches for books based on a query string.
     *
     * @param query The search query.
     * @return A Flow emitting Result with BookSet data or an error.
     */
    fun searchBooks(query: String): Flow<Result<BookSet>>

    /**
     * Retrieves a book by its unique identifier.
     *
     * @param bookId The unique identifier of the book.
     * @return A Flow emitting Result with BookSet data or an error.
     */
    fun getBookById(bookId: String): Flow<Result<BookSet>>

    /**
     * Retrieves books by a specific category with pagination and optional language filtering.
     *
     * @param page The page number for pagination.
     * @param category The category topic.
     * @param language Optional language filter.
     * @return A Flow emitting Result with BookSet data or an error.
     */
    fun getBooksByCategory(
        page: Long,
        category: String,
        language: String? = null
    ): Flow<Result<BookSet>>
}
