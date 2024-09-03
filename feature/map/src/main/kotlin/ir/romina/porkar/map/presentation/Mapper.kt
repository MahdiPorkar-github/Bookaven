package ir.romina.porkar.map.presentation

import com.google.android.gms.maps.model.LatLng
import ir.romina.porkar.map.presentation.home.StationItem
import ir.romina.porkar.model.stations.Station

fun Station.toStationItem(): StationItem {
    return StationItem(
        id = this.id,
        itemPosition = LatLng(this.location.latitude, this.location.longitude),
        itemTitle = this.name,
        itemSnippet = "Capacity: $capacity",
        itemCapacity = this.capacity
    )
}
