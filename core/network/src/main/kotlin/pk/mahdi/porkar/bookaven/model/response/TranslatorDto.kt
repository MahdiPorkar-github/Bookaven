// network/model/Translator.kt

package pk.mahdi.porkar.bookaven.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TranslatorDto(
    @SerialName("name")
    val name: String = "N/A",
    @SerialName("birth_year")
    val birthYear: Int? = null,
    @SerialName("death_year")
    val deathYear: Int? = null
)