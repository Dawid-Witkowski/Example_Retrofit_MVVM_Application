package wingeddev.example.examplezenquotesapiapplication.presentation.mainActivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import wingeddev.example.examplezenquotesapiapplication.domain.model.Quote
import wingeddev.example.examplezenquotesapiapplication.domain.use_case.get_daily_quote.GetDailyQuoteUseCase
import wingeddev.example.examplezenquotesapiapplication.util.Resource
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val getDailyQuoteUseCase: GetDailyQuoteUseCase
): ViewModel() {

    private val observableMutableLiveData = MutableLiveData<List<Quote>>()
    val observableLiveData: LiveData<List<Quote>> = observableMutableLiveData

    init {
        getDailyQuote()
    }

    private fun getDailyQuote() {
        getDailyQuoteUseCase.invoke().onEach { result ->
            when(result) {

                is Resource.Success -> {
                    observableMutableLiveData.postValue(result.data ?: emptyList())
                }

                is Resource.Error ->
                {
                    observableMutableLiveData.postValue(
                        listOf(
                            Quote("Winged Dev", result.message)
                        )
                    )
                }

                is Resource.Loading -> {
                    observableMutableLiveData.postValue(
                        listOf(
                            Quote("", "")
                        )
                    )
                }

            }
        }.launchIn(viewModelScope)
    }
}
