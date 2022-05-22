package wingeddev.example.examplezenquotesapiapplication.data.repository

import wingeddev.example.examplezenquotesapiapplication.data.ZenQuotesApi
import wingeddev.example.examplezenquotesapiapplication.data.model.QuoteDto
import wingeddev.example.examplezenquotesapiapplication.domain.repository.ZenQuotesRepository
import javax.inject.Inject

class ZenQuotesRepositoryImpl @Inject constructor(
    private val api: ZenQuotesApi
): ZenQuotesRepository {
    override suspend fun getDailyQuote(): List<QuoteDto> {
        return api.getDailyQuote()
    }
}