package br.com.augusto.chucknorrisfacts.ui.fact.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import br.com.augusto.chucknorrisfacts.ui.fact.uiState.FactUi

class FactAdapter(
    private val onClickFact: (FactUi) -> Unit,
) : ListAdapter<FactUi, FactHolder>(FactDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactHolder {
        return FactHolder.generate(parent, onClickFact)
    }

    override fun onBindViewHolder(holder: FactHolder, position: Int) {
        getItem(position)
        holder.bind(getItem(position))
    }

    class FactDiffCallBack : DiffUtil.ItemCallback<FactUi>() {
        override fun areItemsTheSame(oldItem: FactUi, newItem: FactUi): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: FactUi, newItem: FactUi): Boolean {
            return oldItem == newItem
        }
    }
}
