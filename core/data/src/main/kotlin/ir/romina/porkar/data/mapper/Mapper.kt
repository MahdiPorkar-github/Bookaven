package ir.romina.porkar.data.mapper

import ir.romina.database.model.StationEntity
import ir.romina.porkar.model.stations.Location
import ir.romina.porkar.model.stations.Station
import ir.romina.porkar.network.model.response.stations.StationDto

fun StationDto.toStation(): Station {
    return Station(
        id = this.stationId,
        name = this.name,
        rentalMethods = this.rentalMethod.split(" "),  // Convert space-separated rental methods into a list
        capacity = this.capacity.toInt(),
        location = Location(
            latitude = this.geocodedColumn.latitude.toDouble(),
            longitude = this.geocodedColumn.longitude.toDouble()
        )
    )
}

fun StationDto.toEntity(): StationEntity {
    return StationEntity(
        id = this.stationId,
        name = this.name,
        rentalMethods = this.rentalMethod.split(" "), // Convert space-separated rental methods into a list
        capacity = this.capacity.toInt(),
        latitude = this.geocodedColumn.latitude.toDouble(),
        longitude = this.geocodedColumn.longitude.toDouble()
    )
}


