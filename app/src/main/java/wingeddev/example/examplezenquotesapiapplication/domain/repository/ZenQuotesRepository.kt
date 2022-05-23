package wingeddev.example.examplezenquotesapiapplication.domain.repository

import wingeddev.example.examplezenquotesapiapplication.data.model.QuoteDto

interface ZenQuotesRepository {
    suspend fun getDailyQuote(): List<QuoteDto>
}