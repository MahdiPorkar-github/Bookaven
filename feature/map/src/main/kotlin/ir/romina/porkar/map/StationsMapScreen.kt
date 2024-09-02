package ir.romina.porkar.map

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.tween
import androidx.compose.runtime.*
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.Circle
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.MapsComposeExperimentalApi
import com.google.maps.android.compose.clustering.Clustering
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

//// In order to make the screens testable and preview-able we separate the state and the content.
//@Composable
//internal fun StationsMapScreenRoute(modifier: Modifier = Modifier) {
//
//
//}


@OptIn(MapsComposeExperimentalApi::class)
@Composable
fun StationsMapScreen(viewModel: StationsViewModel) {
    val uiState by viewModel.uiState.collectAsState()

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
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState
        ) {
            Clustering(
                items = stationItems,
                onClusterClick = {
                    false
                },
                onClusterItemClick = { stationItem ->
                    viewModel.onMapMarkerClicked(stationItem.id) { index ->
                        coroutineScope.launch {
                            // Animate camera with a slightly longer duration
                            cameraPositionState.animate(
                                CameraUpdateFactory.newLatLngZoom(stationItem.itemPosition, 12f),
                            )
                            delay(200) // Small delay before scrolling to the item
                            lazyListState.animateScrollToItem(index)
                        }
                    }
                    false
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

        LazyRow(
            state = lazyListState,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent)
                .padding(8.dp)
        ) {
            items(uiState.stations.size) { index ->
                val station = uiState.stations[index]
                Card(
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable {
                            viewModel.onStationSelected(station) { latLng ->
                                coroutineScope.launch {
                                    cameraPositionState.animate(
                                        CameraUpdateFactory.newLatLngZoom(latLng, 12f),
                                    )
                                    delay(200) // Delay to enhance smoothness
                                    lazyListState.animateScrollToItem(index)
                                }
                            }
                        },
                    colors = CardDefaults.cardColors(
                        containerColor = if (uiState.selectedStation?.id == station.id) Color.Red else Color.White
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(station.name, textAlign = TextAlign.Center)
                        Text("Capacity: ${station.capacity}")
                        Text("Lat: ${station.location.latitude}")
                        Text("Lon: ${station.location.longitude}")
                        Button(onClick = {
                            // Navigate to new screen with station details
                        }) {
                            Text("Navigation")
                        }
                    }
                }
            }
        }
    }
}

// Define a custom scroll animation spec for a smoother experience
val ScrollAnimationSpec: TweenSpec<Float> = tween(
    durationMillis = 500, // Customize duration for scroll animation
    easing = LinearOutSlowInEasing // Smooth easing function
)



