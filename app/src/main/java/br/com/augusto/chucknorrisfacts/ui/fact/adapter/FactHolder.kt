package br.com.augusto.chucknorrisfacts.ui.fact.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.augusto.chucknorrisfacts.R
import br.com.augusto.chucknorrisfacts.databinding.AdapterFactBinding
import br.com.augusto.chucknorrisfacts.ui.fact.uiState.FactUi

class FactHolder(
    private val binding: AdapterFactBinding,
    private val onClickFact: (FactUi) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {
    private var value: TextView = itemView.findViewById(R.id.value)
    private var sharedButton: ImageView = itemView.findViewById(R.id.shared_button)
    private var category: TextView = itemView.findViewById(R.id.category)

    fun bind(factUi: FactUi) {
        with(binding) {
            value.text = factUi.description
            value.textSize = factUi.descriptionSize
            category.text = factUi.category
            sharedButton.setOnClickListener {
                onClickFact.invoke(factUi)
            }
        }
    }

    companion object {
        fun generate(parent: ViewGroup, onClickFact: (FactUi) -> Unit): FactHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = AdapterFactBinding.inflate(inflater, parent, false)
            return FactHolder(binding, onClickFact)
        }
    }
}
