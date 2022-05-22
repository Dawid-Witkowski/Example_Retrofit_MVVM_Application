package wingeddev.example.examplezenquotesapiapplication.data.model

import wingeddev.example.examplezenquotesapiapplication.domain.model.Quote

data class QuoteDto(
    val a: String?,
    val h: String?,
    val q: String?
) {
    fun toQuote(): Quote {
        return Quote(
            a = a,
            q = q
        )
    }
}