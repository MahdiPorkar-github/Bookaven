package ir.romina.porkar.map

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.romina.porkar.data.repository.StationsRepository
import javax.inject.Inject
import androidx.lifecycle.viewModelScope
import ir.romina.porkar.model.stations.Station
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.google.android.gms.maps.model.LatLng

@HiltViewModel
class StationsViewModel @Inject constructor(
    private val repository: StationsRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(StationsUiState())
    val uiState: StateFlow<StationsUiState> = _uiState

    init {
        fetchStations()
    }

    private fun fetchStations() {
        viewModelScope.launch {
            val stations = repository.getStations()
            _uiState.value = _uiState.value.copy(stations = stations)
        }
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


