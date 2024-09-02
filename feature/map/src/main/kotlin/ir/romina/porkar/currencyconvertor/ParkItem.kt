package ir.romina.porkar.currencyconvertor

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem

data class ParkItem(
    val itemPosition: LatLng,
    val itemTitle: String,
    val itemSnippet: String
) : ClusterItem {
    override fun getPosition(): LatLng =
        itemPosition

    override fun getTitle(): String =
        itemTitle

    override fun getSnippet(): String =
        itemSnippet

    override fun getZIndex(): Float? = null
}