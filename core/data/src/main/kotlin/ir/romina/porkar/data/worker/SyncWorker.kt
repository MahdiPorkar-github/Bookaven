package ir.romina.porkar.data.worker

import Synchronizer
import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import ir.romina.porkar.data.repository.StationsRepository

@HiltWorker
class SyncWorker @AssistedInject constructor(
    @Assisted private val appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val stationsRepository: StationsRepository
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        return try {
            stationsRepository.syncWith(Synchronizer())
            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }
    }

    companion object {
        const val NAME = "SyncWorker"

        val request = OneTimeWorkRequestBuilder<SyncWorker>()
            .setConstraints(WorkerUtil.DefaultConstraints)
            .build()
    }

}












