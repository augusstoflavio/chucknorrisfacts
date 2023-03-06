package br.com.augusto.chucknorrisfacts.ui.fact.dialog

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import br.com.augusto.chucknorrisfacts.R
import br.com.augusto.chucknorrisfacts.domain.model.Category
import br.com.augusto.chucknorrisfacts.domain.model.Search
import br.com.augusto.chucknorrisfacts.ui.fact.viewModel.FactsViewModel
import br.com.augusto.chucknorrisfacts.ui.fact.viewModel.SearchFactsViewModel
import br.com.augusto.chucknorrisfacts.ui.dialog.FullScreenDialog
import br.com.augusto.chucknorrisfacts.ui.fact.adapter.CategoryAdapter
import br.com.augusto.chucknorrisfacts.ui.fact.adapter.OnClickCategoryListener
import br.com.augusto.chucknorrisfacts.ui.fact.adapter.OnClickSearchListener
import br.com.augusto.chucknorrisfacts.ui.fact.adapter.SearchAdapter
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class SearchFactsDialog {}

//    private val searchFactsViewModel: SearchFactsViewModel by viewModel()
//    private val factsViewModel: FactsViewModel by sharedViewModel()
//
//    override fun getDialogTitle(): String {
//        return "Search facts"
//    }
//
//    override fun getContent(): Int {
//        return R.layout.dialog_search_facts
//    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        searchFactsViewModel.categories.observe(this) {
//            val categoryAdapter = CategoryAdapter(it, this)
//            suggestions.adapter = categoryAdapter
//            suggestions.layoutManager =
//                StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
//        }
//
//        search.setImeActionLabel("Search", KeyEvent.KEYCODE_ENTER)
//
//        search.setOnEditorActionListener { _, actionId, _ ->
//            if (actionId == EditorInfo.IME_ACTION_DONE) {
//                search(search.text.toString())
//                true
//            } else {
//                false
//            }
//        }
//
//        searchFactsViewModel.lastSearchs.observe(this) {
//            val searchAdapter = SearchAdapter(it, this)
//            last_searchs.adapter = searchAdapter
//            last_searchs.layoutManager = LinearLayoutManager(context)
//            last_searchs.addItemDecoration(
//                DividerItemDecoration(
//                    context,
//                    DividerItemDecoration.VERTICAL
//                )
//            )
//        }
//    }
//
//    override fun onClick(category: Category) {
//        search(category.name!!)
//    }
//
//    override fun onClick(search: Search) {
//        search(search.name!!)
//    }
//
//    private fun search(query: String) {
//        factsViewModel.searchFacts(query)
//        dialog?.dismiss()