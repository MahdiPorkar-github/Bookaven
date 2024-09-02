package ir.romina.porkar.network.service

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import ir.romina.porkar.network.model.response.stations.StationDto
import ir.romina.porkar.network.util.Constants.BASE_URL
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.http.GET

interface StationService {

    @GET("a1935ac4-bfdd-4770-80e5-7909d9fe3e4b")
    suspend fun getStations(): List<StationDto>
}


// for testing purposes
suspend fun main() {
    val json = Json { ignoreUnknownKeys = true }
    val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(OkHttpClient.Builder().addNetworkInterceptor(loggingInterceptor).build())
        .addConverterFactory(json.asConverterFactory(MediaType.get("application/json")))
        .build()

    val service = retrofit.create(StationService::class.java)
    val result = service.getStations()
    println(result)

}