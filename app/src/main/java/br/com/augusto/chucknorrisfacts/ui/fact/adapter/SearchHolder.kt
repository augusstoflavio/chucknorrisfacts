package br.com.augusto.chucknorrisfacts.ui.fact.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.augusto.chucknorrisfacts.R
import br.com.augusto.chucknorrisfacts.domain.model.Search

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