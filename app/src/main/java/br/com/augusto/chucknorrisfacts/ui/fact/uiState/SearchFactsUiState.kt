package br.com.augusto.chucknorrisfacts.ui.fact.uiState

data class SearchFactsUiState(
    val showLoading: Boolean = false,
    val search: String = "",
    val categories: List<CategoryUi> = listOf(),
    val lastSearches: List<SearchUi> = listOf()
)
