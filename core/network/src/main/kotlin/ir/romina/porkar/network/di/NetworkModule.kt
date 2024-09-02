package ir.romina.porkar.network.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.romina.porkar.network.service.StationService
import ir.romina.porkar.network.util.Constants.BASE_URL
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkhttpClient(): OkHttpClient {

        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        // this is the place where you can pass apiKey as well as a header interceptor probably
        return OkHttpClient.Builder().addNetworkInterceptor(loggingInterceptor).build()
    }

    @Provides
    @Singleton
    fun provideStationsRetrofitService(
        client: OkHttpClient
    ): StationService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(Json.asConverterFactory(MediaType.get("application/json")))
            .build()
            .create(StationService::class.java)

    }

}