package br.com.augusto.chucknorrisfacts.modules.fact.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.augusto.chucknorrisfacts.R
import br.com.augusto.chucknorrisfacts.modules.fact.data.model.Fact

class FactAdapter(
    val facts: List<Fact>,
    val onClickListener: OnClickFactListener
): RecyclerView.Adapter<FactAdapter.FactHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactHolder {
        val v = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.adapter_fact, parent, false)
        return FactHolder(v)
    }

    override fun onBindViewHolder(holder: FactHolder, position: Int) {
        val fact = this.facts[position]
        holder.value.text = fact.value

        if (fact.isLongFact()) {
            holder.value.textSize = 18.toFloat()
        } else {
            holder.value.textSize = 25.toFloat()
        }

        holder.sharedButton.setOnClickListener {
            onClickListener.sharedFact(fact)
        }
    }

    override fun getItemCount(): Int {
        return facts.size
    }

    inner class FactHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var value: TextView = itemView.findViewById(R.id.value)
        var sharedButton: ImageView = itemView.findViewById(R.id.shared_button)
    }
}

interface OnClickFactListener {
    fun sharedFact(fact: Fact)
}