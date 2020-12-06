package br.com.augusto.chucknorrisfacts

import android.net.ConnectivityManager
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.augusto.chucknorrisfacts.app.ui.startDialog
import br.com.augusto.chucknorrisfacts.modules.fact.data.model.Fact
import br.com.augusto.chucknorrisfacts.modules.fact.ui.adapter.FactAdapter
import br.com.augusto.chucknorrisfacts.modules.fact.ui.adapter.OnClickFactListener
import br.com.augusto.chucknorrisfacts.modules.fact.ui.dialog.SearchFactsDialog
import br.com.augusto.chucknorrisfacts.modules.fact.ui.viewModel.FactsViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity(), OnClickFactListener {

    val factsViewModel: FactsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        observerFacts()
        observerLoading()
        observerError()

        val connectivityManager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.main_menu, menu)
        return true
    }

    private fun observerFacts() {
        factsViewModel.facts.observe(this, {
            if (it == null) {
                showListMessage("Busque os fatos de chuck norris")
                return@observe
            }

            if (it.isEmpty()) {
                showListMessage("Nenhum fato correspondente a sua busca")
                return@observe
            }

            hideListMessage()

            val adapter = FactAdapter(it, this)
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

    override fun sharedFact(fact: Fact) {
        ShareCompat.IntentBuilder.from(this)
            .setType("text/plain")
            .setText(fact.value)
            .setSubject(fact.url)
            .startChooser()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_search -> {
                openSearch()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun openSearch() {
        startDialog(SearchFactsDialog())
    }

    private fun showListMessage(message: String) {
        facts_list.visibility = View.GONE
        list_message.visibility = View.VISIBLE
        list_message.text = message
    }

    private fun hideListMessage() {
        facts_list.visibility = View.VISIBLE
        list_message.visibility = View.GONE
    }
}