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
): RecyclerView.Adapter<FactHolder>() {

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

class FactHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private var value: TextView = itemView.findViewById(R.id.value)
    private var sharedButton: ImageView = itemView.findViewById(R.id.shared_button)
    private var category: TextView = itemView.findViewById(R.id.category)

    fun setFact(fact: Fact) {
        value.text = fact.value

        if (fact.isLongFact()) {
            value.textSize = 18.toFloat()
        } else {
            value.textSize = 25.toFloat()
        }

        category.text = fact.getCategory()
    }

    fun setOnClickListener(fact: Fact, onClickListener: OnClickFactListener) {
        sharedButton.setOnClickListener {
            onClickListener.sharedFact(fact)
        }
    }
}

interface OnClickFactListener {
    fun sharedFact(fact: Fact)
}