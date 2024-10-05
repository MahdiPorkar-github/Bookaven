// repository/BookRepositoryImpl.kt

package pk.mahdi.bookaven.data.repository

import pk.mahdi.bookaven.data.mapper.toDomainModel
import pk.mahdi.porkar.bookaven.service.BookService
import pk.mahdi.bookaven.model.bookservice.BookSet
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
    private val bookService: BookService
) : BookRepository {

    override suspend fun getAllBooks(page: Long, language: String?): Result<BookSet> {
        try {
            val response = bookService.getAllBooks(page, language).toDomainModel()
            return (Result.success(response))
        } catch (e: Exception) {
            return (Result.failure(e))
        }
    }

    override suspend fun searchBooks(query: String): Result<BookSet> {
        try {
            val response = bookService.searchBooks(query).toDomainModel()
            return (Result.success(response))
        } catch (e: Exception) {
            return (Result.failure(e))
        }
    }

    override suspend fun getBookById(bookId: String): Result<BookSet> {
        try {
            val response = bookService.getBookById(bookId).toDomainModel()
            return (Result.success(response))
        } catch (e: Exception) {
            return (Result.failure(e))
        }
    }

    override suspend fun getBooksByCategory(
        page: Long,
        category: String,
        language: String?
    ): Result<BookSet> {
        try {
            val response = bookService.getBooksByCategory(page, category, language).toDomainModel()
            return (Result.success(response))
        } catch (e: Exception) {
            return (Result.failure(e))
        }
    }
}
