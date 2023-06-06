package br.com.augusto.chucknorrisfacts.ui.fact.extensions

import br.com.augusto.chucknorrisfacts.R
import br.com.augusto.chucknorrisfacts.domain.model.Fact
import br.com.augusto.chucknorrisfacts.ui.fact.uiState.FactUi
import br.com.augusto.chucknorrisfacts.ui.utils.TextUi

fun Fact.toFactUi(): FactUi {
    return FactUi(
        description = TextUi.DirectString(this.value),
        category = this.categories.firstOrNull()?.let {
            TextUi.DirectString(it)
        } ?: TextUi.StringResource(R.string.uncategorized),
        descriptionSize = if (this.value.length > 80) {
            18F
        } else {
            25F
        },
        url = this.url,
    )
}
