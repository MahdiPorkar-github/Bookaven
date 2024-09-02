package ir.romina.porkar.network.model.response.stations

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StationDto(
    @SerialName("station_id") val stationId: String,
    val name: String,
    @SerialName("rental_method") val rentalMethod: String,
    val capacity: String,
    val lat: String,
    val lon: String,
    @SerialName("geocoded_column") val geocodedColumn: GeocodedColumnDto
)

@Serializable
data class GeocodedColumnDto(
    val latitude: String,
    val longitude: String
)
