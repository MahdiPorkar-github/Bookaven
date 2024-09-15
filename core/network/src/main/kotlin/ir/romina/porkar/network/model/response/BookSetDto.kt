// network/model/BookSet.kt

package ir.romina.porkar.network.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BookSetDto(
    @SerialName("count")
    val count: Int = 0,
    @SerialName("next")
    val next: String? = null,
    @SerialName("previous")
    val previous: String? = null,
    @SerialName("results")
    val bookDtos: List<BookDto> = emptyList()
)