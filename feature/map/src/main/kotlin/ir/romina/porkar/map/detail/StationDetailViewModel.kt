package ir.romina.porkar.map.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.romina.porkar.data.repository.StationsRepository
import ir.romina.porkar.model.stations.Location
import ir.romina.porkar.model.stations.Station
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StationDetailViewModel @Inject constructor(
    private val repository: StationsRepository
) : ViewModel() {

    private val _state = MutableStateFlow(StationDetailUiState())
    val state: StateFlow<StationDetailUiState> = _state

    fun loadStation(id: String) = viewModelScope.launch {
        _state.value = _state.value.copy(isLoading = true)
//        val station = repository.getStation(id)
        val station = Station(
            id = id,
            name = "Romina",
            rentalMethods = listOf(""),
            capacity = 10,
            location = Location(38.06735146540299, 46.32490158181274)
        )
        _state.value = _state.value.copy(
            isLoading = false,
            station = station
        )
    }
}
