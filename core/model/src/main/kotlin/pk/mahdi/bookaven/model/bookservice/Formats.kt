package pk.mahdi.bookaven.model.bookservice

data class Formats(
    val textPlain: String = "",
    val applicationXMobipocketEbook: String? = null,
    val textHtml: String? = null,
    val applicationOctetStream: String? = null,
    val textPlainCharsetUsAscii: String? = null,
    val applicationEpubZip: String? = null,
    val imageJpeg: String? = null,
    val applicationRdfXml: String? = null,
    val textHtmlCharsetIso88591: String? = null,
    val textHtmlCharsetUtf8: String? = null,
    val textPlainCharsetUtf8: String? = null,
    val textPlainCharsetIso88591: String? = null
)
