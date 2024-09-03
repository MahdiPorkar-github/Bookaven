package ir.romina.porkar.map.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ir.romina.porkar.designsystem.theme.Typography
import ir.romina.porkar.model.stations.Station

@Composable
fun StationDetailScreen(
    stationId: String,
    viewModel: StationDetailViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

//    LaunchedEffect(key1 = stationId) {
//        viewModel.loadStation(stationId)
//    }

    if (state.isLoading) {
        CircularProgressIndicator(modifier = Modifier.wrapContentSize())
    } else if (state.station != null) {
        StationDetailContent(state.station!!)
    } else {
        // TODO: Handle error or empty state
    }
}

@Composable
fun StationDetailContent(station: Station) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = station.name,
            textAlign = TextAlign.Center,
            style = Typography.headlineLarge
        )

        Text(
            text = "Capacity: ${station.capacity}",
            style = Typography.labelSmall
        )

        Text(
            text = "Lat: ${station.location.latitude}",
            style = Typography.labelSmall
        )
        Text(
            text = "Lon: ${station.location.longitude}",
            style = Typography.labelSmall
        )
    }
}
