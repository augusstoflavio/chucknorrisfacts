package br.com.augusto.chucknorrisfacts.ui.fact.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.augusto.chucknorrisfacts.R
import br.com.augusto.chucknorrisfacts.domain.model.Fact

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