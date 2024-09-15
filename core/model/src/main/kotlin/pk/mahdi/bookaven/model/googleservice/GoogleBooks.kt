package pk.mahdi.bookaven.model.googleservice

data class GoogleBooks(
    val totalItems: Int,
    val books: List<GoogleBook>
)

data class GoogleBook(
    val volumeInfo: VolumeInfo
)


data class VolumeInfo(
    val imageLinks: ImageLinks?,
    val pageCount: Int = 0,
    val description: String = ""
)


data class ImageLinks(
    val thumbnail: String
)


