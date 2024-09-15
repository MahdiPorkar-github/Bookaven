package pk.mahdi.bookaven.model.bookservice

data class BookSet(
    val count: Int,
    val next: String?,
    val previous: String?,
    val books: List<Book>
)
