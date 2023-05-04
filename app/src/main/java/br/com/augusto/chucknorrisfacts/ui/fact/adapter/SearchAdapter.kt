package br.com.augusto.chucknorrisfacts.ui.fact.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import br.com.augusto.chucknorrisfacts.ui.fact.uiState.SearchUi

class SearchAdapter(
    private val onClickSearch: (SearchUi) -> Unit,
) : ListAdapter<SearchUi, SearchHolder>(SearchDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHolder {
        return SearchHolder.generate(parent, onClickSearch)
    }

    override fun onBindViewHolder(holder: SearchHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class SearchDiffCallBack : DiffUtil.ItemCallback<SearchUi>() {
        override fun areItemsTheSame(oldItem: SearchUi, newItem: SearchUi): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: SearchUi, newItem: SearchUi): Boolean {
            return oldItem == newItem
        }
    }
}
