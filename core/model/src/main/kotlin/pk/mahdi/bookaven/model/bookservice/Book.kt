package pk.mahdi.bookaven.model.bookservice

data class Book(
    val id: Int,
    val title: String,
    val authors: List<Author>,
    val translators: List<Translator>,
    val subjects: List<String>,
    val bookshelves: List<String>,
    val languages: List<String>,
    val copyright: Boolean,
    val mediaType: String,
    val formats: Formats,
    val downloadCount: Long
)
