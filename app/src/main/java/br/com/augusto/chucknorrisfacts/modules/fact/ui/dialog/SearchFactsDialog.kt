package br.com.augusto.chucknorrisfacts.modules.fact.ui.dialog

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import br.com.augusto.chucknorrisfacts.R
import br.com.augusto.chucknorrisfacts.app.ui.FullScreenDialog
import br.com.augusto.chucknorrisfacts.modules.fact.data.model.Category
import br.com.augusto.chucknorrisfacts.modules.fact.data.model.Search
import br.com.augusto.chucknorrisfacts.modules.fact.ui.adapter.CategoryAdapter
import br.com.augusto.chucknorrisfacts.modules.fact.ui.adapter.OnClickCategoryListener
import br.com.augusto.chucknorrisfacts.modules.fact.ui.adapter.OnClickSearchListener
import br.com.augusto.chucknorrisfacts.modules.fact.ui.adapter.SearchAdapter
import br.com.augusto.chucknorrisfacts.modules.fact.ui.viewModel.FactsViewModel
import br.com.augusto.chucknorrisfacts.modules.fact.ui.viewModel.SearchFactsViewModel
import kotlinx.android.synthetic.main.dialog_search_facts.*
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class SearchFactsDialog: FullScreenDialog(), OnClickCategoryListener, OnClickSearchListener {

    val searchFactsViewModel: SearchFactsViewModel by viewModel()
    val factsViewModel: FactsViewModel by sharedViewModel()

    override fun getDialogTitle(): String {
        return "Search facts"
    }

    override fun getContent(): Int {
        return R.layout.dialog_search_facts
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchFactsViewModel.categories.observe(this, {
            val categoryAdapter = CategoryAdapter(it, this)
            suggestions.adapter = categoryAdapter
            suggestions.layoutManager =
                StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        })

        search.setImeActionLabel("Search", KeyEvent.KEYCODE_ENTER)
        search.setOnKeyListener { _, keyCode, _ ->
            if (keyCode == KeyEvent.KEYCODE_ENTER) {
                search(search.text.toString())
            }

            return@setOnKeyListener false
        }

        searchFactsViewModel.lastSearchs.observe(this, {
            val searchAdapter = SearchAdapter(it, this)
            last_searchs.adapter = searchAdapter
            last_searchs.layoutManager = LinearLayoutManager(context)
            last_searchs.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        })
    }

    override fun onClick(category: Category) {
        search(category.name!!)
    }

    override fun onClick(search: Search) {
        search(search.name!!)
    }

    private fun search(query: String) {
        factsViewModel.searchFacts(query)
        dialog?.dismiss()
    }
}