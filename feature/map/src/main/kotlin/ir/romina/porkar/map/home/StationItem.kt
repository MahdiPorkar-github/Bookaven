package ir.romina.porkar.map.home

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem

data class StationItem(
    val id: String,
    val itemPosition: LatLng,
    val itemTitle: String,
    val itemSnippet: String,
    val itemCapacity: Int
) : ClusterItem {

    override fun getPosition(): LatLng = itemPosition

    override fun getTitle(): String = itemTitle

    override fun getSnippet(): String = itemSnippet

    override fun getZIndex(): Float? = null
}
