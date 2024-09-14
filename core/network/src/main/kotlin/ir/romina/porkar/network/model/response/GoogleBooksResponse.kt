// network/model/GoogleBooksResponse.kt

package ir.romina.porkar.network.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GoogleBooksResponse(
    @SerialName("totalItems")
    val totalItems: Int,
    
    @SerialName("items")
    val items: List<GoogleBookItem>? = null
)

@Serializable
data class GoogleBookItem(
    @SerialName("volumeInfo")
    val volumeInfo: VolumeInfo
)

@Serializable
data class VolumeInfo(
    @SerialName("imageLinks")
    val imageLinks: ImageLinks? = null,
    
    @SerialName("pageCount")
    val pageCount: Int? = null,
    
    @SerialName("description")
    val description: String? = null
)

@Serializable
data class ImageLinks(
    @SerialName("thumbnail")
    val thumbnail: String
)
