package ir.romina.porkar.map.home

import androidx.compose.runtime.*
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.Circle
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.MapsComposeExperimentalApi
import com.google.maps.android.compose.clustering.Clustering
import ir.romina.porkar.map.components.StationCard
import ir.romina.porkar.map.toStationItem
import ir.romina.porkar.model.stations.Station
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// In order to make the screens testable and preview-able we separate the state and the content.

@Composable
fun StationsMapScreenRoute(
    modifier: Modifier = Modifier,
    viewModel: StationsViewModel = hiltViewModel(),
    onStationClick: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    StationsMapScreen(
        uiState = uiState,
        onStationSelected = viewModel::onStationSelected,
        onMapMarkerClicked = viewModel::onMapMarkerClicked,
        onStationClick = onStationClick,
        modifier = modifier
    )
}

@Composable
fun StationsMapScreen(
    uiState: StationsUiState,
    onStationSelected: (Station, (LatLng) -> Unit) -> Unit,
    onMapMarkerClicked: (String, (Int) -> Unit) -> Unit,
    onStationClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val initialLocation = LatLng(38.06735146540299, 46.32490158181274)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(initialLocation, 12f)
    }
    val coroutineScope = rememberCoroutineScope()
    val lazyListState = rememberLazyListState()

    val stationItems = remember(uiState.stations) {
        uiState.stations.map { it.toStationItem() }
    }

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        MapWithStations(
            stationItems = stationItems,
            uiState = uiState,
            cameraPositionState = cameraPositionState,
            onMapMarkerClicked = onMapMarkerClicked,
            coroutineScope = coroutineScope,
            lazyListState = lazyListState
        )

        StationList(
            stations = uiState.stations,
            selectedStation = uiState.selectedStation,
            onStationSelected = onStationSelected,
            onStationClick = onStationClick,
            lazyListState = lazyListState,
            cameraPositionState = cameraPositionState,
            coroutineScope = coroutineScope
        )
    }
}

@Composable
fun StationList(
    stations: List<Station>,
    selectedStation: Station?,
    onStationSelected: (Station, (LatLng) -> Unit) -> Unit,
    onStationClick: (String) -> Unit,
    lazyListState: LazyListState,
    cameraPositionState: CameraPositionState,
    coroutineScope: CoroutineScope
) {
    LazyRow(
        state = lazyListState,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent)
            .padding(8.dp)
    ) {
        items(stations.size) { index ->
            val station = stations[index]
            StationCard(
                station = station,
                isSelected = selectedStation?.id == station.id,
                onClick = {
                    onStationSelected(station) { latLng ->
                        coroutineScope.launch {
                            cameraPositionState.animate(
                                CameraUpdateFactory.newLatLngZoom(latLng, 12f),
                            )
                            delay(200)
                            lazyListState.animateScrollToItem(index)
                        }
                    }
                },
                onNavigate = { onStationClick(station.id) }
            )
        }
    }
}

@OptIn(MapsComposeExperimentalApi::class)
@Composable
fun MapWithStations(
    stationItems: List<StationItem>,
    uiState: StationsUiState,
    cameraPositionState: CameraPositionState,
    onMapMarkerClicked: (String, (Int) -> Unit) -> Unit,
    coroutineScope: CoroutineScope,
    lazyListState: LazyListState
) {
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {
        Clustering(
            items = stationItems,
            onClusterClick = { false },
            onClusterItemClick = { stationItem ->
                onMapMarkerClicked(stationItem.id) { index ->
                    coroutineScope.launch {
                        cameraPositionState.animate(
                            CameraUpdateFactory.newLatLngZoom(stationItem.itemPosition, 12f),
                        )
                        delay(200)
                        lazyListState.animateScrollToItem(index)
                    }
                }
                true
            }
        )

        stationItems.forEach { stationItem ->
            Circle(
                center = stationItem.itemPosition,
                radius = stationItem.itemCapacity * 50.0,
                fillColor = if (uiState.selectedStation?.id == stationItem.id) Color.Red else Color.Green,
                strokeColor = Color.Transparent
            )
        }
    }
}



