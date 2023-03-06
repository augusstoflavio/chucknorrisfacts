package br.com.augusto.chucknorrisfacts.ui.fact.uiState

data class FactsUiState(
    val showLoading: Boolean = false,
    val facts: List<FactUi> = listOf(),
    val search: String? = null,
    val showFactsList: Boolean = false,
    val showMessageToSearchFact: Boolean = true,
    val showMessageNoFactForSearch: Boolean = false
)
