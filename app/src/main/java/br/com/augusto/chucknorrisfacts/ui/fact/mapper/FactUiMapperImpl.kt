package br.com.augusto.chucknorrisfacts.ui.fact.mapper

import br.com.augusto.chucknorrisfacts.R
import br.com.augusto.chucknorrisfacts.domain.model.Fact
import br.com.augusto.chucknorrisfacts.ui.fact.uiState.FactUi
import br.com.augusto.chucknorrisfacts.ui.utils.TextUi

class FactUiMapperImpl : FactUiMapper {

    override fun fromModel(fact: Fact): FactUi {
        return FactUi(
            description = TextUi.DirectString(fact.value),
            category = fact.categories.firstOrNull()?.let {
                TextUi.DirectString(it)
            } ?: TextUi.StringResource(R.string.uncategorized),
            descriptionSize = if (fact.value.length > 80) {
                18F
            } else {
                25F
            },
            url = fact.url,
        )
    }
}
