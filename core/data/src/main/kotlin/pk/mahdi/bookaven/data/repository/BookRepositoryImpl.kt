// repository/BookRepositoryImpl.kt

package pk.mahdi.bookaven.data.repository

import pk.mahdi.bookaven.data.mapper.toDomainModel
import ir.romina.porkar.network.service.BookService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import pk.mahdi.bookaven.model.bookservice.BookSet
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
    private val bookService: BookService
) : BookRepository {

    override fun getAllBooks(page: Long, language: String?): Flow<Result<BookSet>> = flow {
        try {
            val response = bookService.getAllBooks(page, language).toDomainModel()
            emit(Result.success(response))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    override fun searchBooks(query: String): Flow<Result<BookSet>> = flow {
        try {
            val response = bookService.searchBooks(query).toDomainModel()
            emit(Result.success(response))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    override fun getBookById(bookId: String): Flow<Result<BookSet>> = flow {
        try {
            val response = bookService.getBookById(bookId).toDomainModel()
            emit(Result.success(response))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    override fun getBooksByCategory(page: Long, category: String, language: String?): Flow<Result<BookSet>> = flow {
        try {
            val response = bookService.getBooksByCategory(page, category, language).toDomainModel()
            emit(Result.success(response))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
}
