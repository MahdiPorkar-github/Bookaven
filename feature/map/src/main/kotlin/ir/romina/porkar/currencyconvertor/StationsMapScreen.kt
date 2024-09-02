package ir.romina.porkar.currencyconvertor

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.Circle
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapsComposeExperimentalApi
import com.google.maps.android.compose.clustering.Clustering
import com.google.maps.android.compose.rememberCameraPositionState
import ir.romina.porkar.designsystem.theme.MahdiPkTheme

// In order to make the screens testable and preview-able we separate the state and the content.
@Composable
internal fun StationsMapScreenRoute(modifier: Modifier = Modifier) {


}

@OptIn(MapsComposeExperimentalApi::class)
@Composable
internal fun StationsMapScreen() {

    MahdiPkTheme {
        val hydePark = LatLng(51.508610, -0.163611)
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(hydePark, 10f)
        }
        val parkMarkers = remember {
            mutableStateListOf(
                ParkItem(
                    hydePark,
                    "Hyde Park",
                    "Marker in hyde Park"
                ),
            )
        }

        var selectedMarker: ParkItem? by remember { mutableStateOf(null) }
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState
            ) {
                Clustering(items = parkMarkers,
                    onClusterClick = {
                        // Handle when the cluster is clicked
                        false
                    }, onClusterItemClick = { marker ->
                        // Handle when a marker in the cluster is clicked
                        selectedMarker = marker
                        false
                    })


                parkMarkers.forEach {
                    Circle(
                        center = it.position,
                        radius = 120.0,
                        fillColor = Color.Green,
                        strokeColor = Color.Green
                    )
                }
            }
        }

    }
}


