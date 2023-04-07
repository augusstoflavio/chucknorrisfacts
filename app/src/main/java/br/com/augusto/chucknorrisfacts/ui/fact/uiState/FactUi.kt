package br.com.augusto.chucknorrisfacts.ui.fact.uiState

import br.com.augusto.chucknorrisfacts.ui.utils.TextUi

data class FactUi(
    val description: TextUi,
    val category: TextUi,
    val descriptionSize: Float,
    val url: String
)
