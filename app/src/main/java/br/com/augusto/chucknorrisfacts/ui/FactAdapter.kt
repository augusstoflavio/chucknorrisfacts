package br.com.augusto.chucknorrisfacts.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.augusto.chucknorrisfacts.R
import br.com.augusto.chucknorrisfacts.domain.model.Fact
import br.com.augusto.chucknorrisfacts.ui.fact.adapter.FactHolder
import br.com.augusto.chucknorrisfacts.ui.fact.adapter.OnClickFactListener

class FactAdapter(
    val facts: List<Fact>,
    private val onClickListener: OnClickFactListener
) : RecyclerView.Adapter<FactHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_fact, parent, false)
        return FactHolder(v)
    }

    override fun onBindViewHolder(holder: FactHolder, position: Int) {
        val fact = this.facts[position]
        holder.setFact(fact)
        holder.setOnClickListener(fact, onClickListener)
    }

    override fun getItemCount(): Int {
        return facts.size
    }
}