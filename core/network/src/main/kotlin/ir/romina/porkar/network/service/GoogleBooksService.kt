// network/service/GoogleBooksService.kt

package ir.romina.porkar.network.service

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import ir.romina.porkar.network.model.response.GoogleBooksResponse
import ir.romina.porkar.network.util.Constants
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import pk.mahdi.network.BuildConfig
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

interface GoogleBooksService {
    @GET("volumes")
    suspend fun getExtraInfo(
        @Query("q") query: String,
        @Query("startIndex") startIndex: Int = 0,
        @Query("maxResults") maxResults: Int = 1,
        @Query("key") apiKey: String
    ): GoogleBooksResponse
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
        .baseUrl(Constants.GOOGLE_BOOKS_BASE_URL)
        .client(OkHttpClient.Builder().addNetworkInterceptor(loggingInterceptor).build())
        .addConverterFactory(json.asConverterFactory(MediaType.get("application/json")))
        .build()

    val service = retrofit.create(GoogleBooksService::class.java)

    try {
        // Make the network request
        val result: GoogleBooksResponse = service.getExtraInfo(
            query =  "Kotlin programming"  ,
            maxResults = 5,
            startIndex = 0,
            apiKey = BuildConfig.GOOGLE_API_KEY
        )

        // Print the results
        println(result.toString())

    } catch (e: Exception) {
        // Handle exceptions (e.g., network errors, serialization issues)
        println("Error occurred: ${e.localizedMessage}")
        e.printStackTrace()
    }
}