package br.com.augusto.chucknorrisfacts.ui.fact.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.augusto.chucknorrisfacts.R
import br.com.augusto.chucknorrisfacts.domain.model.Category

class CategoryAdapter(
    val categories: List<Category>,
    private val onClickCategoryListener: OnClickCategoryListener
) : RecyclerView.Adapter<CategoryHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_category, parent, false)
        return CategoryHolder(v)
    }

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        val category = this.categories[position]
        holder.setCategory(category)
        holder.setOnClickCategotyListener(category, onClickCategoryListener)
    }

    override fun getItemCount(): Int {
        return categories.size
    }
}