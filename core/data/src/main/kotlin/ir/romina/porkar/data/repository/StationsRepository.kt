package ir.romina.porkar.data.repository

import Syncable
import ir.romina.porkar.model.stations.Station
import kotlinx.coroutines.flow.Flow

// define an abstraction layer because of dependency inversion principle of SOLID
interface StationsRepository : Syncable {

    // use flow to receive the changes in a reactive manner
    fun getStations(): Flow<List<Station>>

    suspend fun getStationById(id: String) : Station?
}