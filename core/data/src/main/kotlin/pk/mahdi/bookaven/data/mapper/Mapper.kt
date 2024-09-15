package pk.mahdi.bookaven.data.mapper

import ir.romina.porkar.network.model.response.AuthorDto
import ir.romina.porkar.network.model.response.BookDto
import ir.romina.porkar.network.model.response.BookSetDto
import ir.romina.porkar.network.model.response.FormatsDto
import ir.romina.porkar.network.model.response.GoogleBookItemResponse
import ir.romina.porkar.network.model.response.GoogleBooksResponse
import ir.romina.porkar.network.model.response.ImageLinksResponse
import ir.romina.porkar.network.model.response.TranslatorDto
import ir.romina.porkar.network.model.response.VolumeInfoResponse
import pk.mahdi.bookaven.model.bookservice.Author
import pk.mahdi.bookaven.model.bookservice.Book
import pk.mahdi.bookaven.model.bookservice.BookSet
import pk.mahdi.bookaven.model.bookservice.Formats
import pk.mahdi.bookaven.model.bookservice.Translator
import pk.mahdi.bookaven.model.googleservice.GoogleBook
import pk.mahdi.bookaven.model.googleservice.GoogleBooks
import pk.mahdi.bookaven.model.googleservice.ImageLinks
import pk.mahdi.bookaven.model.googleservice.VolumeInfo


fun BookSetDto.toDomainModel(): BookSet {
    return BookSet(
        count = this.count,
        next = this.next,
        previous = this.previous,
        books = this.bookDtos.map { it.toDomainModel() }
    )
}

fun BookDto.toDomainModel(): Book {
    return Book(
        id = this.id,
        title = this.title ,
        authors = this.authorDtos.map { it.toDomainModel() },
        translators = this.translatorDtos.map { it.toDomainModel() },
        subjects = this.subjects ,
        bookshelves = this.bookshelves ,
        languages = this.languages,
        copyright = this.copyright ?: true,
        mediaType = this.mediaType,
        formats = this.formats?.toDomainModel() ?: Formats(),
        downloadCount = this.downloadCount
    )
}

fun AuthorDto.toDomainModel(): Author {
    return Author(
        name = this.name,
        birthYear = this.birthYear ?: -1,
        deathYear = this.deathYear ?: -1
    )
}

fun TranslatorDto.toDomainModel(): Translator {
    return Translator(
        name = this.name,
        birthYear = this.birthYear ?: -1,
        deathYear = this.deathYear ?: -1
    )
}

fun FormatsDto.toDomainModel(): Formats {
    return Formats(
        textPlain = this.textplain ?: "",
        applicationXMobipocketEbook = this.applicationxMobipocketEbook ?: "",
        textHtml = this.texthtml ?: "",
        applicationOctetStream = this.applicationoctetStream ?: "",
        textPlainCharsetUsAscii = this.textplainCharsetusAscii ?: "",
        applicationEpubZip = this.applicationepubzip ?: "",
        imageJpeg = this.imagejpeg ?: "",
        applicationRdfXml = this.applicationrdfxml ?: "",
        textHtmlCharsetIso88591 = this.texthtmlCharsetiso88591 ?: "",
        textHtmlCharsetUtf8 = this.texthtmlCharsetutf8 ?: "",
        textPlainCharsetUtf8 = this.textplainCharsetutf8 ?: "",
        textPlainCharsetIso88591 = this.textplainCharsetiso88591 ?: ""
    )
}

fun GoogleBooksResponse.toDomainModel(): GoogleBooks {
    return GoogleBooks(
        totalItems = this.totalItems,
        books = this.items?.map { it.toDomainModel() } ?: emptyList()
    )
}

fun GoogleBookItemResponse.toDomainModel(): GoogleBook {
    return GoogleBook(
        volumeInfo = this.volumeInfoResponse.toDomainModel()
    )
}
fun VolumeInfoResponse.toDomainModel(): VolumeInfo {
    return VolumeInfo(
        imageLinks = this.imageLinksResponse?.toDomainModel(),
        pageCount = this.pageCount ?: 0,
        description = this.description ?: ""
    )
}

fun ImageLinksResponse.toDomainModel(): ImageLinks {
    return ImageLinks(
        thumbnail = this.thumbnail  // Non-nullable, directly mapped
    )
}



