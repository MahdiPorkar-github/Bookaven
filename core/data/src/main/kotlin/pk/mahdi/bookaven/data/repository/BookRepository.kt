package pk.mahdi.bookaven.data.repository

import pk.mahdi.bookaven.model.bookservice.BookSet

interface BookRepository {
    /**
     * Retrieves all books with pagination and optional language filtering.
     *
     * @param page The page number for pagination.
     * @param language Optional language filter.
     * @return A Flow emitting Result with BookSet data or an error.
     */
    suspend fun getAllBooks(
        page: Long,
        language: String? = null
    ): Result<BookSet>

    /**
     * Searches for books based on a query string.
     *
     * @param query The search query.
     * @return A Flow emitting Result with BookSet data or an error.
     */
    suspend fun searchBooks(query: String): Result<BookSet>

    /**
     * Retrieves a book by its unique identifier.
     *
     * @param bookId The unique identifier of the book.
     * @return A Flow emitting Result with BookSet data or an error.
     */
    suspend fun getBookById(bookId: String): Result<BookSet>

    /**
     * Retrieves books by a specific category with pagination and optional language filtering.
     *
     * @param page The page number for pagination.
     * @param category The category topic.
     * @param language Optional language filter.
     * @return A Flow emitting Result with BookSet data or an error.
     */
    suspend fun getBooksByCategory(
        page: Long,
        category: String,
        language: String? = null
    ): Result<BookSet>
}
