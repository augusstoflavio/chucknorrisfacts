package br.com.augusto.chucknorrisfacts.ui.fact.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.augusto.chucknorrisfacts.R
import br.com.augusto.chucknorrisfacts.domain.model.Category

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