package ir.romina.porkar.map.presentation.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ir.romina.porkar.currencyconvertor.R
import ir.romina.porkar.designsystem.components.StationTextWithBullet
import ir.romina.porkar.model.stations.Station

@Composable
fun StationDetailScreen(
    stationId: String,
    viewModel: StationDetailViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()


    if (state.isLoading) {
        CircularProgressIndicator(modifier = Modifier.wrapContentSize())
    } else if (state.station != null) {
        StationDetailContent(state.station!!)
    }
}

@Composable
fun StationDetailContent(station: Station) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(text = stringResource(R.string.here_is_the_detailed_information))

        Spacer(modifier = Modifier.height(32.dp))

        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start
        ){
            StationTextWithBullet(text = stringResource(R.string.name, station.name), bulletColor = Color(0xFFF28C28))
            StationTextWithBullet(text = stringResource(R.string.capacity, station.capacity), bulletColor = Color.Gray)
            StationTextWithBullet(text = stringResource(R.string.lat, station.location.latitude), bulletColor = Color(0xFFFFBC3D))
            StationTextWithBullet(text = stringResource(R.string.lon, station.location.longitude), bulletColor = Color(0xFF87CEEB))
        }

    }
}
