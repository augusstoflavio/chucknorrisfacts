package br.com.augusto.chucknorrisfacts.ui.fact.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.augusto.chucknorrisfacts.databinding.AdapterSearchBinding
import br.com.augusto.chucknorrisfacts.ui.fact.uiState.SearchUi

class SearchHolder(
    private val binding: AdapterSearchBinding,
    private val onClickSearch: (SearchUi) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(search: SearchUi) {
        binding.search.text = search.name
        binding.root.setOnClickListener {
            onClickSearch.invoke(search)
        }
    }

    companion object {
        fun generate(parent: ViewGroup, onClickSearch: (SearchUi) -> Unit): SearchHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = AdapterSearchBinding.inflate(inflater, parent, false)
            return SearchHolder(binding, onClickSearch)
        }
    }
}
