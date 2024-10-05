// network/model/GoogleBooksResponse.kt

package pk.mahdi.porkar.bookaven.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GoogleBooksResponse(
    @SerialName("totalItems")
    val totalItems: Int,
    
    @SerialName("items")
    val items: List<GoogleBookItemResponse>? = null
)

@Serializable
data class GoogleBookItemResponse(
    @SerialName("volumeInfo")
    val volumeInfoResponse: VolumeInfoResponse
)

@Serializable
data class VolumeInfoResponse(
    @SerialName("imageLinks")
    val imageLinksResponse: ImageLinksResponse? = null,

    @SerialName("pageCount")
    val pageCount: Int? = null,

    @SerialName("description")
    val description: String? = null
)

@Serializable
data class ImageLinksResponse(
    @SerialName("thumbnail")
    val thumbnail: String
)
