package ir.romina.porkar.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.romina.porkar.data.repository.StationsRepository
import ir.romina.porkar.data.repository.OfflineFirstStationsRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindStationRepository(impl: OfflineFirstStationsRepositoryImpl) : StationsRepository
}