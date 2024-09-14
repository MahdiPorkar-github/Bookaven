// network/di/ApiKeyModule.kt

package ir.romina.porkar.network.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pk.mahdi.network.BuildConfig
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiKeyModule {

    @Provides
    @Singleton
    fun provideGoogleApiKey(): String = BuildConfig.GOOGLE_API_KEY
}
