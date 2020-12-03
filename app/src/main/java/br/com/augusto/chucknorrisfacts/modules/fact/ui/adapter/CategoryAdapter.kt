package br.com.augusto.chucknorrisfacts.modules.fact.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.augusto.chucknorrisfacts.R
import br.com.augusto.chucknorrisfacts.modules.fact.data.model.Category

class CategoryAdapter(
    val categories: List<Category>,
    val onClickCategoryListener: OnClickCategoryListener
): RecyclerView.Adapter<CategoryHolder>() {

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

class CategoryHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private var categoryText: TextView = itemView.findViewById(R.id.category)

    fun setCategory(category: Category) {
        categoryText.text = category.name
    }

    fun setOnClickCategotyListener(
        category: Category,
        onClickCategoryListener: OnClickCategoryListener
    ) {
        itemView.setOnClickListener {
            onClickCategoryListener.onClick(category)
        }
    }
}

interface OnClickCategoryListener {
    fun onClick(category: Category)
}