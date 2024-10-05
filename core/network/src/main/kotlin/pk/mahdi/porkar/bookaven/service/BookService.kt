// network/service/BookService.kt

package pk.mahdi.porkar.bookaven.service

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import pk.mahdi.porkar.bookaven.model.response.BookSetDto
import pk.mahdi.porkar.bookaven.util.Constants
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

interface BookService {
    

    @GET("books/")
    suspend fun getAllBooks(
        @Query("page") page: Long,
        @Query("languages") language: String? = null
    ): BookSetDto

    @GET("books/")
    suspend fun searchBooks(
        @Query("search") query: String
    ): BookSetDto

    @GET("books/")
    suspend fun getBookById(
        @Query("ids") bookId: String
    ): BookSetDto

    @GET("books/")
    suspend fun getBooksByCategory(
        @Query("page") page: Long,
        @Query("topic") category: String,
        @Query("languages") language: String? = null
    ): BookSetDto
    
}

// for testing purposes
suspend fun main() {
    val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }
    val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    val retrofit = Retrofit.Builder()
        .baseUrl(Constants.GUTENDEX_BASE_URL)
        .client(OkHttpClient.Builder().addNetworkInterceptor(loggingInterceptor).build())
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()

    val service = retrofit.create(BookService::class.java)
    val result = service.getAllBooks(1)
    println(result)

}

