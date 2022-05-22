package wingeddev.example.examplezenquotesapiapplication.presentation.mainActivity

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import wingeddev.example.examplezenquotesapiapplication.data.model.QuoteDto
import wingeddev.example.examplezenquotesapiapplication.domain.model.Quote
import wingeddev.example.examplezenquotesapiapplication.domain.repository.ZenQuotesRepository
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val repository: ZenQuotesRepository
): ViewModel() {
    fun getDailyQuote(): LiveData<List<Quote>> {
        val result = MutableLiveData<List<Quote>>()
        viewModelScope.launch {
            val returnedValue = repository.getDailyQuote().map { it.toQuote() }
            result.postValue(returnedValue)
        }
        return result
    }
}