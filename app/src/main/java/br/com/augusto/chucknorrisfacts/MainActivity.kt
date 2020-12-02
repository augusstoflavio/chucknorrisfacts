package br.com.augusto.chucknorrisfacts

import android.os.Bundle
import android.view.View
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

        factsViewModel.facts.observe(this, {
           var adapter = FactAdapter(it)
            facts_list.adapter = adapter
            facts_list.layoutManager = LinearLayoutManager(applicationContext)
        })

        factsViewModel.searchFacts("word")

        factsViewModel.loading.observe(this, {
            if (it) {
                progress_bar.visibility = View.VISIBLE
            } else {
                progress_bar.visibility = View.GONE
            }
        })
    }
}