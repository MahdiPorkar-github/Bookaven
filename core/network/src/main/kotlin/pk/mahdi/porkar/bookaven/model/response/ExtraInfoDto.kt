// network/model/ExtraInfo.kt

package pk.mahdi.porkar.bookaven.model.response

import kotlinx.serialization.Serializable

@Serializable
data class ExtraInfoDto(
    val coverImage: String = "",
    val pageCount: Int = 0,
    val description: String = ""
)
