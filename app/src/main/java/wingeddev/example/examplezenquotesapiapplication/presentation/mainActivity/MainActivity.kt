package wingeddev.example.examplezenquotesapiapplication.presentation.mainActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import wingeddev.example.examplezenquotesapiapplication.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        viewModel.observableLiveData.observe(this) { listOfQuotes ->
            // the API call returns a list containing only one quote, that's why [0] is used
            binding.authorTV.text = listOfQuotes[0].a
            binding.quoteTV.text = listOfQuotes[0].q
        }
        setContentView(binding.root)
    }
}