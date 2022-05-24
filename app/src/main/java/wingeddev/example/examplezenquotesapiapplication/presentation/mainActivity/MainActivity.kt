package wingeddev.example.examplezenquotesapiapplication.presentation.mainActivity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import wingeddev.example.examplezenquotesapiapplication.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModels()

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        viewModel.observableLiveData.observe(this) { listOfQuotes ->
            // the API call returns a list containing only one quote, that's why [0] is used
            binding.authorTV.text = listOfQuotes[0].a
            binding.quoteTV.text = listOfQuotes[0].q
        }

        binding.button.setOnClickListener {
            // on application the visibility == View.INVISIBLE, because android animate doesn't
            // work always as it is supposed to on views "outside" the screen (for the first
            // time only)
            binding.copyTV.visibility = View.VISIBLE

            lifecycleScope.launch {
                binding.copyTV.animate().translationY(80f).duration = 200L
                delay(1500L)
                binding.copyTV.animate().translationY(-80f).duration = 200L
            }
        }

        setContentView(binding.root)
    }
}