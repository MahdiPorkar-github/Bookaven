package pk.mahdi.bookaven.data.repository

import pk.mahdi.porkar.bookaven.model.response.GoogleBooksResponse
import pk.mahdi.porkar.bookaven.service.GoogleBooksService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GoogleBooksRepositoryImpl @Inject constructor(
    private val googleBooksService: GoogleBooksService,
    private val apiKey: String,
) : GoogleBooksRepository {

    override fun searchBooks(
        query: String,
        maxResults: Int,
        startIndex: Int
    ): Flow<Result<GoogleBooksResponse>> = flow {
        try {
            // Make the network request
            val response = googleBooksService.getExtraInfo(
                query = query,
                maxResults = maxResults,
                startIndex = startIndex,
                apiKey = apiKey
            )
            // Emit the successful response
            emit(Result.success(response))
        } catch (e: Exception) {
            // Emit the error
            emit(Result.failure(e))
        }
    }
}
