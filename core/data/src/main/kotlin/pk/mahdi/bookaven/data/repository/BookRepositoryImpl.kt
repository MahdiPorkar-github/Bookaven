package pk.mahdi.bookaven.data.repository

import pk.mahdi.bookaven.DataError
import  pk.mahdi.bookaven.Result
import pk.mahdi.bookaven.data.mapper.toDomainModel
import pk.mahdi.bookaven.model.bookservice.BookSet
import pk.mahdi.porkar.bookaven.service.BookService
import java.io.IOException
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
    private val bookService: BookService
) : BookRepository {

    override suspend fun getAllBooks(
        page: Long,
        language: String?
    ): Result<BookSet, DataError.Network> {
        return try {
            val response = bookService.getAllBooks(page, language).toDomainModel()
            Result.Success(response)
        } catch (e: Exception) {
            val dataError = mapExceptionToDataError(e)
           Result.Error(dataError)
        }
    }

    override suspend fun searchBooks(query: String): Result<BookSet, DataError.Network> {
        return try {
            val response = bookService.searchBooks(query).toDomainModel()
            Result.Success(response)
        } catch (e: Exception) {
            val dataError = mapExceptionToDataError(e)
            Result.Error(dataError)
        }
    }

    override suspend fun getBookById(bookId: String): Result<BookSet, DataError.Network> {
        return try {
            val response = bookService.getBookById(bookId).toDomainModel()
            Result.Success(response)
        } catch (e: Exception) {
            val dataError = mapExceptionToDataError(e)
            Result.Error(dataError)
        }
    }

    override suspend fun getBooksByCategory(
        page: Long,
        category: String,
        language: String?
    ): Result<BookSet, DataError.Network> {
        return try {
            val response = bookService.getBooksByCategory(page, category, language).toDomainModel()
            Result.Success(response)
        } catch (e: Exception) {
            val dataError = mapExceptionToDataError(e)
            Result.Error(dataError)
        }
    }

    /**
     * Maps different types of exceptions to corresponding DataError.Network enums.
     *
     * @param exception The exception to map.
     * @return The corresponding DataError.Network enum.
     */
    private fun mapExceptionToDataError(exception: Exception): DataError.Network {
        return when (exception) {
            is IOException -> {
                // Typically represents network failures like no internet
                DataError.Network.NO_INTERNET
            }

            else -> {
                // Fallback for any other exceptions
                DataError.Network.UNKNOWN
            }
        }
    }

}
