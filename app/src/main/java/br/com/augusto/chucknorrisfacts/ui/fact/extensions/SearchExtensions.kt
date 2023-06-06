package br.com.augusto.chucknorrisfacts.ui.fact.extensions

import br.com.augusto.chucknorrisfacts.domain.model.Search
import br.com.augusto.chucknorrisfacts.ui.fact.uiState.SearchUi

fun Search.toSearchUi(): SearchUi {
    return SearchUi(
        name = this.name,
    )
}