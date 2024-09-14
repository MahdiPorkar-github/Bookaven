// network/model/ExtraInfo.kt

package ir.romina.porkar.network.model.response

import kotlinx.serialization.Serializable

@Serializable
data class ExtraInfo(
    val coverImage: String = "",
    val pageCount: Int = 0,
    val description: String = ""
)
