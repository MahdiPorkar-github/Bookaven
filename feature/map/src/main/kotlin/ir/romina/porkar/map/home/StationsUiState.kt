package ir.romina.porkar.map.home

import ir.romina.porkar.model.stations.Station

data class StationsUiState(
    val stations: List<Station> = emptyList(),
    val selectedStation: Station? = null
)
