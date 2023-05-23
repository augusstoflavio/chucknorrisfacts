package br.com.augusto.chucknorrisfacts.ui.main.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.augusto.chucknorrisfacts.databinding.ActivityMainBinding
import br.com.augusto.chucknorrisfacts.ui.extensions.showError
import br.com.augusto.chucknorrisfacts.ui.main.uiEvent.MainUiEvent
import br.com.augusto.chucknorrisfacts.ui.main.viewModel.MainViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupObservers()
        viewModel.onUiEvent(MainUiEvent.OnInitScreen)
    }

    private fun setupObservers() {
        viewModel.uiError.observe(this) {
            this.showError(it.error) {
                viewModel.onUiEvent(it.tryAgainUiEvent)
            }
        }
    }
}
