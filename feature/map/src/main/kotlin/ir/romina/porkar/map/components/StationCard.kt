package ir.romina.porkar.map.components

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ir.romina.porkar.designsystem.components.StationTextWithBullet
import ir.romina.porkar.designsystem.theme.Typography
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
            StationTextWithBullet(text = "Name: ${station.name}", bulletColor = Color(0xFFF28C28))
            StationTextWithBullet(text = "Capacity: ${station.capacity}", bulletColor = Color.Gray)
            StationTextWithBullet(text = "Lat: ${station.location.latitude}", bulletColor = Color(
                0xFFFFBC3D
            )
            )
            StationTextWithBullet(text = "Lon: ${station.location.longitude}", bulletColor = Color(0xFF87CEEB))
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = onNavigate,
                shape = RoundedCornerShape(8.dp),
            ) {
                Text("Navigation", style = Typography.titleSmall)
            }
        }
    }
}



