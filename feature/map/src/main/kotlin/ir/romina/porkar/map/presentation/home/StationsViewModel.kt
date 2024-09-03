package ir.romina.porkar.map.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.romina.porkar.data.repository.StationsRepository
import ir.romina.porkar.data.worker.SyncManager
import ir.romina.porkar.model.stations.Station
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.retryWhen
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class StationsViewModel @Inject constructor(
    private val repository: StationsRepository,
    workManagerSyncManager: SyncManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(StationsUiState())
    val uiState: StateFlow<StationsUiState> = _uiState

    init {
        workManagerSyncManager.isSyncing.onEach { isLoading ->
            _uiState.update {
                it.copy(isLoading = isLoading)
            }
        }.launchIn(viewModelScope)

        fetchStations()
    }

    private fun fetchStations() {
        repository.getStations()
            .retryWhen { cause, _ -> cause is IllegalStateException }
            .combine(_uiState.map { it.searchQuery }) { stations, query ->
                if (query.isBlank()) {
                    stations
                } else {
                    stations.filter { it.name.contains(query, ignoreCase = true) }
                }
            }
            .onEach { stations: List<Station> ->
                if (stations.isEmpty()) return@onEach
                _uiState.update { it.copy(stations = stations) }
            }.launchIn(viewModelScope)
    }

    fun onSearchQueryChanged(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
    }

    fun onStationSelected(station: Station, moveCamera: (LatLng) -> Unit) {
        _uiState.update { it.copy(selectedStation = station) }
        moveCamera(LatLng(station.location.latitude, station.location.longitude))
    }

    fun onMapMarkerClicked(stationId: String, scrollToItem: (Int) -> Unit) {
        val stationIndex = _uiState.value.stations.indexOfFirst { it.id == stationId }
        if (stationIndex != -1) {
            val station = _uiState.value.stations[stationIndex]
            _uiState.update { it.copy(selectedStation = station) }
            scrollToItem(stationIndex)
        }
    }
}

