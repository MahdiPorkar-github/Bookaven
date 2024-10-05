package pk.mahdi.bookaven.data.repository

import pk.mahdi.porkar.bookaven.model.response.GoogleBooksResponse
import kotlinx.coroutines.flow.Flow

interface GoogleBooksRepository {
    /**
     * Searches for books based on the provided query.
     *
     * @param query The search query (e.g., "Kotlin programming").
     * @param maxResults The maximum number of results to return.
     * @param startIndex The starting index for pagination.
     * @return A Flow emitting GoogleBooksResponse data.
     */
    fun searchBooks(
        query: String,
        maxResults: Int,
        startIndex: Int
    ): Flow<Result<GoogleBooksResponse>>
}
