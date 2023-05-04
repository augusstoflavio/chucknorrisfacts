package br.com.augusto.chucknorrisfacts.ui.fact.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.augusto.chucknorrisfacts.databinding.AdapterCategoryBinding
import br.com.augusto.chucknorrisfacts.ui.fact.uiState.CategoryUi

class CategoryHolder(
    private val binding: AdapterCategoryBinding,
    private val onClickCategory: (CategoryUi) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(category: CategoryUi) {
        binding.category.text = category.name
        binding.root.setOnClickListener {
            onClickCategory.invoke(category)
        }
    }

    companion object {
        fun generate(parent: ViewGroup, onClickCategory: (CategoryUi) -> Unit): CategoryHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = AdapterCategoryBinding.inflate(inflater, parent, false)
            return CategoryHolder(binding, onClickCategory)
        }
    }
}