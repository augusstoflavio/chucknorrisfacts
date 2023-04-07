package br.com.augusto.chucknorrisfacts.ui.fact.mapper

import br.com.augusto.chucknorrisfacts.domain.model.Fact
import br.com.augusto.chucknorrisfacts.ui.fact.uiState.FactUi

class FactUiMapperImpl : FactUiMapper {

    override fun fromModel(fact: Fact): FactUi {
        return FactUi(
            description = fact.value,
            category = fact.categories.firstOrNull() ?: "UNCATEGORIZED",
            descriptionSize = if (fact.value.length > 80) {
                18F
            } else {
                25F
            },
            url = fact.url,
        )
    }
}
