package br.com.augusto.chucknorrisfacts.ui.fact.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.augusto.chucknorrisfacts.R
import br.com.augusto.chucknorrisfacts.domain.model.Search

interface OnClickSearchListener {
    fun onClick(search: Search)
}
