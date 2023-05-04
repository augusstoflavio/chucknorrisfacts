package br.com.augusto.chucknorrisfacts.ui.fact.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import br.com.augusto.chucknorrisfacts.ui.fact.uiState.CategoryUi

class CategoryAdapter(
    private val onClickCategory: (CategoryUi) -> Unit,
) : ListAdapter<CategoryUi, CategoryHolder>(CategoryDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        return CategoryHolder.generate(parent, onClickCategory)
    }

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class CategoryDiffCallBack : DiffUtil.ItemCallback<CategoryUi>() {
        override fun areItemsTheSame(oldItem: CategoryUi, newItem: CategoryUi): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: CategoryUi, newItem: CategoryUi): Boolean {
            return oldItem == newItem
        }
    }
}
