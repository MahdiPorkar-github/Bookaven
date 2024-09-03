package ir.romina.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import ir.romina.database.model.StationEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface StationDao {
    
    @Upsert
    suspend fun upsertStation(station: StationEntity)

    @Upsert
    suspend fun upsertStations(stations: List<StationEntity>)

    @Query("SELECT * FROM stations")
    fun getAllStations(): Flow<List<StationEntity>>

    @Query("SELECT * FROM stations WHERE id = :stationId")
    suspend fun getStationById(stationId: String): StationEntity?

    @Query("DELETE FROM stations")
    suspend fun deleteAllStations()
}
