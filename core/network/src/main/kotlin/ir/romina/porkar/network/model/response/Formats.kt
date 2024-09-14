
package ir.romina.porkar.network.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Formats(
    @SerialName("text/plain")
    val textplain: String? = null,
    @SerialName("application/x-mobipocket-ebook")
    val applicationxMobipocketEbook: String? = null,
    @SerialName("text/html")
    val texthtml: String? = null,
    @SerialName("application/octet-stream")
    val applicationoctetStream: String? = null,
    @SerialName("text/plain; charset=us-ascii")
    val textplainCharsetusAscii: String? = null,
    @SerialName("application/epub+zip")
    val applicationepubzip: String? = null,
    @SerialName("image/jpeg")
    val imagejpeg: String? = null,
    @SerialName("application/rdf+xml")
    val applicationrdfxml: String? = null,
    @SerialName("text/html; charset=iso-8859-1")
    val texthtmlCharsetiso88591: String? = null,
    @SerialName("text/html; charset=utf-8")
    val texthtmlCharsetutf8: String? = null,
    @SerialName("text/plain; charset=utf-8")
    val textplainCharsetutf8: String? = null,
    @SerialName("text/plain; charset=iso-8859-1")
    val textplainCharsetiso88591: String? = null
)