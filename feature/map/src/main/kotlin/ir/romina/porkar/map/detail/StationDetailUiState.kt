package ir.romina.porkar.map.detail

import ir.romina.porkar.model.stations.Station

data class StationDetailUiState(
    val isLoading: Boolean = false,
    val station: Station? = null
)
