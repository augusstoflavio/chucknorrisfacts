package br.com.augusto.chucknorrisfacts.ui.fact.extensions

import br.com.augusto.chucknorrisfacts.domain.model.Category
import br.com.augusto.chucknorrisfacts.ui.fact.uiState.CategoryUi

fun Category.toCategoryUi(): CategoryUi {
    return CategoryUi(
        name = this.name,
    )
}
