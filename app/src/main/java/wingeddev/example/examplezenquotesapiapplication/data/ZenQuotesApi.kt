package wingeddev.example.examplezenquotesapiapplication.data

import retrofit2.http.GET
import wingeddev.example.examplezenquotesapiapplication.data.model.QuoteDto

interface ZenQuotesApi {
    @GET("/api/today")
    suspend fun getDailyQuote(): List<QuoteDto>
}