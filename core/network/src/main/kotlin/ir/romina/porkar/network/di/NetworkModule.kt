// network/di/NetworkModule.kt

package ir.romina.porkar.network.di

import android.content.Context
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ir.romina.porkar.network.service.BookService
import ir.romina.porkar.network.service.GoogleBooksService
import ir.romina.porkar.network.util.Constants
import kotlinx.serialization.json.Json
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    // Provide JSON configuration
    @Provides
    @Singleton
    fun provideJson(): Json = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }

    @Provides
    @Singleton
    fun provideCacheDir(@ApplicationContext context: Context): File {
        // Provide the cache directory for OkHttp
        return File(context.cacheDir, "http_cache")
    }

    // Provide CacheInterceptor
    @Provides
    @Singleton
    fun provideCacheInterceptor(): Interceptor = Interceptor { chain ->
        val response = chain.proceed(chain.request())
        val cacheControl = response.header("Cache-Control")

        if (cacheControl == null || cacheControl.contains("no-store") || cacheControl.contains("no-cache")
            || cacheControl.contains("must-revalidate") || cacheControl.contains("max-age=0")
        ) {
            response.newBuilder()
                .header("Cache-Control", "public, max-age=${60 * 60 * 24 * 7}") // 1 week
                .build()
        } else {
            response
        }
    }

    // Provide OkHttpClient
    @Provides
    @Singleton
    fun provideOkHttpClient(cacheDir: File, cacheInterceptor: Interceptor): OkHttpClient {
        val cache = Cache(File(cacheDir, "http-cache"), 32L * 1024 * 1024) // 32 MiB

        val builder = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(100, TimeUnit.SECONDS)
            .cache(cache)
            .addInterceptor(cacheInterceptor)

        // Add logging interceptor in debug builds
        if (/* BuildConfig.DEBUG */ true) { // Replace with actual BuildConfig check
            val logging = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            builder.addInterceptor(logging)
        }

        return builder.build()
    }

    // Provide Retrofit for GutenDex API
    @Provides
    @Singleton
    @Named("GutenDex")
    fun provideGutenDexRetrofit(json: Json, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.GUTENDEX_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory(MediaType.get("application/json")))
            .build()
    }

    @Provides
    @Singleton
    @Named("GoogleBooks")
    fun provideGoogleBooksRetrofit(json: Json, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.GOOGLE_BOOKS_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory(MediaType.get("application/json")))
            .build()
    }

    // Provide BookApiService using the GutenDex Retrofit instance
    @Provides
    @Singleton
    fun provideBookApiService(@Named("GutenDex") retrofit: Retrofit): BookService {
        return retrofit.create(BookService::class.java)
    }

    // Provide GoogleBooksService using the Google Books Retrofit instance
    @Provides
    @Singleton
    fun provideGoogleBooksService(@Named("GoogleBooks") retrofit: Retrofit): GoogleBooksService {
        return retrofit.create(GoogleBooksService::class.java)
    }


}
