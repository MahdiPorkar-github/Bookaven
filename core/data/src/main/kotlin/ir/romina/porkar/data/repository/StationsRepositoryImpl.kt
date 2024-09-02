package ir.romina.porkar.data.repository

import ir.romina.porkar.data.mapper.toStation
import ir.romina.porkar.model.stations.Station
import ir.romina.porkar.network.service.StationService
import javax.inject.Inject

class StationsRepositoryImpl @Inject constructor(
    private val stationService: StationService
) : StationsRepository {
    override suspend fun getStations(): List<Station> {
        return stationService.getStations().map { stationDto ->
            stationDto.toStation()
        }
    }
}