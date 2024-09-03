package ir.romina.porkar.map.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ir.romina.porkar.currencyconvertor.R
import ir.romina.porkar.designsystem.components.StationTextWithBullet
import ir.romina.porkar.designsystem.theme.MahdiPkTheme
import ir.romina.porkar.designsystem.theme.Typography
import ir.romina.porkar.model.stations.Location
import ir.romina.porkar.model.stations.Station

@Composable
fun StationCard(
    station: Station,
    isSelected: Boolean,
    onClick: (() -> Unit),
    onNavigate: (() -> Unit)
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .size(width = 150.dp, height = 170.dp)
            .clickable(onClick = { onClick.invoke() }),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) Color.Red else Color.White
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start
        ) {
            StationTextWithBullet(
                text = "Name: ${station.name}",
                bulletColor = Color(0xFFF28C28),
                modifier = Modifier.fillMaxWidth()
            )
            StationTextWithBullet(
                text = "Capacity: ${station.capacity}",
                bulletColor = Color.Gray,
                modifier = Modifier.fillMaxWidth()
            )
            StationTextWithBullet(
                text = "Lat: ${station.location.latitude}",
                bulletColor = Color(0xFFFFBC3D),
                modifier = Modifier.fillMaxWidth()
            )
            StationTextWithBullet(
                text = "Lon: ${station.location.longitude}",
                bulletColor = Color(0xFF87CEEB),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = onNavigate,
                shape = RoundedCornerShape(8.dp),
            ) {
                Text(text = stringResource(R.string.navigation), style = Typography.titleSmall)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StationCardPreview() {
    MahdiPkTheme {
        // Sample station data
        val sampleStation = Station(
            id = "1",
            name = "Central Station",
            capacity = 100,
            location = Location(latitude = 37.7749, longitude = -122.4194),
            rentalMethods = listOf("")
        )

        // State to simulate selection
        var isSelected = remember { false }

        StationCard(
            station = sampleStation,
            isSelected = isSelected,
            onClick = {
                // Simulate selection toggle
                isSelected = !isSelected
            },
            onNavigate = {
                // Simulate navigation action, e.g., print to console
                println("Navigating to ${sampleStation.name}")
            }
        )
    }
}

