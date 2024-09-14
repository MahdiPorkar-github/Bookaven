// repository/BookRepositoryImpl.kt

package ir.romina.porkar.data.repository

import ir.romina.porkar.network.model.response.BookSet
import ir.romina.porkar.network.service.BookService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
    private val bookService: BookService
) : BookRepository {

    override fun getAllBooks(page: Long, language: String?): Flow<Result<BookSet>> = flow {
        try {
            val response = bookService.getAllBooks(page, language)
            emit(Result.success(response))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    override fun searchBooks(query: String): Flow<Result<BookSet>> = flow {
        try {
            val response = bookService.searchBooks(query)
            emit(Result.success(response))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    override fun getBookById(bookId: String): Flow<Result<BookSet>> = flow {
        try {
            val response = bookService.getBookById(bookId)
            emit(Result.success(response))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    override fun getBooksByCategory(page: Long, category: String, language: String?): Flow<Result<BookSet>> = flow {
        try {
            val response = bookService.getBooksByCategory(page, category, language)
            emit(Result.success(response))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
}
