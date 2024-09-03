package ir.romina.porkar

import android.app.Application
import android.content.Context
import androidx.work.Configuration
import androidx.work.ExistingWorkPolicy
import androidx.work.ListenableWorker
import androidx.work.WorkManager
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import dagger.hilt.android.HiltAndroidApp
import ir.romina.porkar.data.repository.StationsRepository
import ir.romina.porkar.data.worker.SyncWorker
import javax.inject.Inject

@HiltAndroidApp
class MyApp: Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: MyHiltWorkerFactor

    override fun onCreate() {
        super.onCreate()

        WorkManager.getInstance(this).beginUniqueWork(
            SyncWorker.NAME,
            ExistingWorkPolicy.KEEP,
            SyncWorker.request
        ).enqueue()
    }

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()

}

class MyHiltWorkerFactor @Inject constructor(
    private val stationsRepository: StationsRepository
): WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {
        return when(workerClassName) {
            SyncWorker::class.java.name -> SyncWorker(appContext, workerParameters, stationsRepository)
            else -> null
        }
    }

}