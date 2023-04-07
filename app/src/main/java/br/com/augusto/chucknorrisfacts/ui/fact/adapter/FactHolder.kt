package br.com.augusto.chucknorrisfacts.ui.fact.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.augusto.chucknorrisfacts.databinding.AdapterFactBinding
import br.com.augusto.chucknorrisfacts.ui.fact.uiState.FactUi

class FactHolder(
    private val binding: AdapterFactBinding,
    private val onClickFact: (FactUi) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(factUi: FactUi) {
        with(binding) {
            val context = binding.root.context
            value.text = factUi.description.toString(context)
            value.textSize = factUi.descriptionSize
            category.text = factUi.category.toString(context)
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
