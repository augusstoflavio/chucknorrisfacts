package br.com.augusto.chucknorrisfacts.ui.fact.uiEvent

import br.com.augusto.chucknorrisfacts.ui.fact.uiState.CategoryUi
import br.com.augusto.chucknorrisfacts.ui.fact.uiState.SearchUi

sealed interface SearchFactsUiEvent {
    object OnInitScreen : SearchFactsUiEvent
    object OnClickTryAgainLoadLastSearches : SearchFactsUiEvent
    object OnClickTryAgainLoadCategories : SearchFactsUiEvent
    data class OnChangeSearch(val search: String) : SearchFactsUiEvent
    data class OnClickCategory(val category: CategoryUi) : SearchFactsUiEvent
    data class OnClickLastSearch(val search: SearchUi) : SearchFactsUiEvent
}
