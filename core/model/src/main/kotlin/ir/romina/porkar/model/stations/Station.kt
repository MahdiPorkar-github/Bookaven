package ir.romina.porkar.model.stations

data class Station(
    val id: String,
    val name: String,
    val rentalMethods: List<String>,
    val capacity: Int,
    val location: Location
)

data class Location(
    val latitude: Double,
    val longitude: Double
)