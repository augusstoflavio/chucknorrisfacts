package br.com.augusto.chucknorrisfacts.ui.fact.uiState

import br.com.augusto.chucknorrisfacts.domain.model.Fact

object FactBuilder {

    fun buildUi(): FactUi {
        return FactUi(
            description = "description",
            category = "category",
            descriptionSize = 25F
        )
    }

    fun buildModel(): Fact {
        return Fact(
            categories = listOf("category"),
            value = "description",
            url = "http://fact.com/1"
        )
    }
}
