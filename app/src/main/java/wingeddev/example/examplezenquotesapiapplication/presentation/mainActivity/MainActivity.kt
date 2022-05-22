package wingeddev.example.examplezenquotesapiapplication.presentation.mainActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import wingeddev.example.examplezenquotesapiapplication.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        viewModel.getDailyQuote().observe(this) { listOfQuotes ->
            /* just for clarity: there is only one quote provided by the api, but it is sent in a
            json list, so it has to be done this way */
            binding.authorTV.text = listOfQuotes[0].a
            binding.quoteTV.text = listOfQuotes[0].q
        }
        setContentView(binding.root)
    }
}