package br.com.augusto.chucknorrisfacts

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.augusto.chucknorrisfacts.modules.fact.ui.adapter.FactAdapter
import br.com.augusto.chucknorrisfacts.modules.fact.ui.viewModel.FactsViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    val factsViewModel: FactsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        observerFacts()
        observerLoading()
        observerError()

        factsViewModel.searchFacts("word")
    }

    private fun observerFacts() {
        factsViewModel.facts.observe(this, {
            val adapter = FactAdapter(it)
            facts_list.adapter = adapter
            facts_list.layoutManager = LinearLayoutManager(applicationContext)
        })
    }

    private fun observerError() {
        factsViewModel.error.observe(this, {
            Toast.makeText(applicationContext, it, Toast.LENGTH_SHORT).show()
        })
    }

    private fun observerLoading() {
        factsViewModel.loading.observe(this, {
            if (it) {
                progress_bar.visibility = View.VISIBLE
            } else {
                progress_bar.visibility = View.GONE
            }
        })
    }
}