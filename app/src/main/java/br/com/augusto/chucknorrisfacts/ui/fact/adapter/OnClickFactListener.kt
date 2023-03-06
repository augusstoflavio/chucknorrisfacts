package br.com.augusto.chucknorrisfacts.ui.fact.adapter

import br.com.augusto.chucknorrisfacts.domain.model.Fact

interface OnClickFactListener {
    fun sharedFact(fact: Fact)
}
