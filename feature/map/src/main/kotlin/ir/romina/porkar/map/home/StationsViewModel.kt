package ir.romina.porkar.map.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.romina.porkar.data.repository.StationsRepository
import ir.romina.porkar.data.worker.SyncManager
import ir.romina.porkar.model.stations.Station
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
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
            .retryWhen {cause,_ -> cause is IllegalStateException}
            .onEach { stations: List<Station> ->
            if (stations.isEmpty()) return@onEach
            _uiState.value = _uiState.value.copy(stations = stations)
        }.launchIn(viewModelScope)
    }

    fun onStationSelected(station: Station, moveCamera: (LatLng) -> Unit) {
        _uiState.value = _uiState.value.copy(selectedStation = station)
        moveCamera(LatLng(station.location.latitude, station.location.longitude))
    }

    fun onMapMarkerClicked(stationId: String, scrollToItem: (Int) -> Unit) {
        val stationIndex = _uiState.value.stations.indexOfFirst { it.id == stationId }
        if (stationIndex != -1) {
            val station = _uiState.value.stations[stationIndex]
            _uiState.value = _uiState.value.copy(selectedStation = station)
            scrollToItem(stationIndex)
        }
    }
}


