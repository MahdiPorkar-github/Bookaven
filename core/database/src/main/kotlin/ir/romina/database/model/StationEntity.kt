package ir.romina.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import ir.romina.porkar.model.stations.Location
import ir.romina.porkar.model.stations.Station

@Entity(tableName = "stations")
data class StationEntity(
    @PrimaryKey val id: String,
    val name: String,
    val rentalMethods: List<String>,
    val capacity: Int,
    val latitude: Double,
    val longitude: Double
)

fun StationEntity.toStation(): Station {
    return Station(
        id = this.id,
        name = this.name,
        rentalMethods = this.rentalMethods,
        capacity = this.capacity,
        location = Location(latitude = this.latitude, longitude = this.longitude)
    )
}