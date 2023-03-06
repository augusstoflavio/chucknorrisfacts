package br.com.augusto.chucknorrisfacts.ui.fact.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.augusto.chucknorrisfacts.R
import br.com.augusto.chucknorrisfacts.domain.model.Search

class SearchAdapter(
    private val searchs: List<Search>,
    private val onClickSearchListener: OnClickSearchListener
) : RecyclerView.Adapter<SearchHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_search, parent, false)
        return SearchHolder(v)
    }

    override fun onBindViewHolder(holder: SearchHolder, position: Int) {
        val category = this.searchs[position]
        holder.setSearch(category)
        holder.setOnClickSearchListener(category, onClickSearchListener)
    }

    override fun getItemCount(): Int {
        return searchs.size
    }
}