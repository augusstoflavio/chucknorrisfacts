package br.com.augusto.chucknorrisfacts.ui.fact.mapper

import br.com.augusto.chucknorrisfacts.domain.model.Fact
import br.com.augusto.chucknorrisfacts.ui.fact.uiState.FactUi

interface FactUiMapper {

    fun fromModel(fact: Fact): FactUi
}
