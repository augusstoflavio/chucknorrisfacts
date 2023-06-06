package br.com.augusto.chucknorrisfacts.builder

import br.com.augusto.chucknorrisfacts.domain.model.Search
import br.com.augusto.chucknorrisfacts.ui.fact.uiState.SearchUi

object SearchBuilder {

    fun buildUi(): SearchUi {
        return SearchUi(
            name = "Elwood Gross",
        )
    }

    fun buildModel(): Search {
        return Search(
            name = "Elwood Gross",
        )
    }
}
