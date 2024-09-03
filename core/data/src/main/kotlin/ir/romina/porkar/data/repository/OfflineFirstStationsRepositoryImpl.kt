package ir.romina.porkar.data.repository

import Synchronizer
import ir.romina.database.db.AppDatabase
import ir.romina.database.model.StationEntity
import ir.romina.database.model.toStation
import ir.romina.porkar.data.mapper.toEntity
import ir.romina.porkar.data.mapper.toStation
import ir.romina.porkar.model.stations.Station
import ir.romina.porkar.network.service.StationService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class OfflineFirstStationsRepositoryImpl @Inject constructor(
    private val stationService: StationService,
    database: AppDatabase,
) : StationsRepository {

    val dao = database.stationDao()


    override fun getStations(): Flow<List<Station>> {
        return dao.getAllStations().map { stationEntities ->
            stationEntities.map { it.toStation() }
        }
    }

    override suspend fun getStationById(id: String): Station? {
        return dao.getStationById(id)?.toStation()
    }

    override suspend fun syncWith(synchronizer: Synchronizer) {
        synchronizer.start(
            fetchRemoteData = { stationService.getStations() },
            deleteLocalData = { dao.deleteAllStations() },
            updateLocalData = { stationDtos ->
                dao.upsertStations(stationDtos.map {
                    it.toEntity()
                })
            }
        )
    }
}