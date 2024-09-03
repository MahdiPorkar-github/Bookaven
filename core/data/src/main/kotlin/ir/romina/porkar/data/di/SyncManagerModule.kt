package ir.romina.porkar.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.romina.porkar.data.repository.StationsRepository
import ir.romina.porkar.data.repository.OfflineFirstStationsRepositoryImpl
import ir.romina.porkar.data.worker.SyncManager
import ir.romina.porkar.data.worker.WorkManagerSyncManager

@Module
@InstallIn(SingletonComponent::class)
abstract class SyncManagerModule {

    @Binds
    abstract fun bindStationSyncManager(impl: WorkManagerSyncManager) : SyncManager
}