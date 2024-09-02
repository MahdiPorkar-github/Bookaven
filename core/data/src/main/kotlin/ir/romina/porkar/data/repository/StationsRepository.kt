package ir.romina.porkar.data.repository

import ir.romina.porkar.model.stations.Station

// define an abstraction layer because of dependency inversion principle of SOLID
interface StationsRepository {

    suspend fun getStations(): List<Station>
}