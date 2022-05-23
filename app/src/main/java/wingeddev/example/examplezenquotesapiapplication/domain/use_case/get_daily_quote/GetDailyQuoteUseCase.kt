package wingeddev.example.examplezenquotesapiapplication.domain.use_case.get_daily_quote

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import wingeddev.example.examplezenquotesapiapplication.domain.model.Quote
import wingeddev.example.examplezenquotesapiapplication.domain.repository.ZenQuotesRepository
import wingeddev.example.examplezenquotesapiapplication.util.Resource
import java.io.IOException
import javax.inject.Inject

class GetDailyQuoteUseCase @Inject constructor(
    private val repository: ZenQuotesRepository
) {
    operator fun invoke(): Flow<Resource<List<Quote>>> = flow {
        try {
            emit(Resource.Loading())
            val dailyQuote = repository.getDailyQuote().map { it.toQuote() }
            emit(Resource.Success(dailyQuote))
        } catch (e: HttpException) {
            emit(Resource.Error("An unexpected error occurred! \uD83D\uDE31"))
        } catch (e: IOException) {
            emit(Resource.Error("Server unreachable, check your internet connection \uD83E\uDD15"))
        }
    }
}