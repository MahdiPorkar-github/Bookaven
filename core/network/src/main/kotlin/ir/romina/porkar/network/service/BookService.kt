// network/service/BookService.kt

package ir.romina.porkar.network.service

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import ir.romina.porkar.network.model.response.BookSet
import ir.romina.porkar.network.util.Constants
import kotlinx.serialization.json.Json
import okhttp3.MediaType
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
    ): BookSet

    @GET("books/")
    suspend fun searchBooks(
        @Query("search") query: String
    ): BookSet

    @GET("books/")
    suspend fun getBookById(
        @Query("ids") bookId: String
    ): BookSet

    @GET("books/")
    suspend fun getBooksByCategory(
        @Query("page") page: Long,
        @Query("topic") category: String,
        @Query("languages") language: String? = null
    ): BookSet
    
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
        .addConverterFactory(json.asConverterFactory(MediaType.get("application/json")))
        .build()

    val service = retrofit.create(BookService::class.java)
    val result = service.getAllBooks(1)
    println(result)

}

