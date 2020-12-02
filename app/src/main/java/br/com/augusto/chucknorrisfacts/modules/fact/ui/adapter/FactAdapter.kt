package br.com.augusto.chucknorrisfacts.modules.fact.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.augusto.chucknorrisfacts.R
import br.com.augusto.chucknorrisfacts.modules.fact.data.model.Fact

class FactAdapter(val facts: List<Fact>): RecyclerView.Adapter<FactAdapter.FactHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactHolder {
        val v = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.adapter_fact, parent, false)
        return FactHolder(v)
    }

    override fun onBindViewHolder(holder: FactHolder, position: Int) {
        val fact = this.facts[position]
        holder.value.text = fact.value
    }

    override fun getItemCount(): Int {
        return facts.size
    }

    inner class FactHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var value: TextView = itemView.findViewById(R.id.value)
    }
}