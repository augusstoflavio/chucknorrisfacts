package br.com.augusto.chucknorrisfacts.modules.fact.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.augusto.chucknorrisfacts.R
import br.com.augusto.chucknorrisfacts.modules.fact.data.model.Search

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

class SearchHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private var searchText: TextView = itemView.findViewById(R.id.search)

    fun setSearch(search: Search) {
        searchText.text = search.name
    }

    fun setOnClickSearchListener(
        search: Search,
        onClickSearchListener: OnClickSearchListener
    ) {
        itemView.setOnClickListener {
            onClickSearchListener.onClick(search)
        }
    }
}

interface OnClickSearchListener {
    fun onClick(search: Search)
}
