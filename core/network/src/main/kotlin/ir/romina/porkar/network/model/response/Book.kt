// network/model/Book.kt

package ir.romina.porkar.network.model.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Book(
    @SerialName("id")
    val id: Int = 0,
    @SerialName("title")
    val title: String = "",
    @SerialName("authors")
    val authors: List<Author> = emptyList(),
    @SerialName("translators")
    val translators: List<Translator> = emptyList(),
    @SerialName("subjects")
    val subjects: List<String> = emptyList(),
    @SerialName("bookshelves")
    val bookshelves: List<String> = emptyList(),
    @SerialName("languages")
    val languages: List<String> = emptyList(),
    @SerialName("copyright")
    val copyright: Boolean? = null,
    @SerialName("media_type")
    val mediaType: String = "",
    @SerialName("formats")
    val formats: Formats? = null,
    @SerialName("download_count")
    val downloadCount: Long = 0
)