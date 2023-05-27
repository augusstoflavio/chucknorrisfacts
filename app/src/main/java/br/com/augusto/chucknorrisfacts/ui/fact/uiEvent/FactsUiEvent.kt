package br.com.augusto.chucknorrisfacts.ui.fact.uiEvent

import br.com.augusto.chucknorrisfacts.ui.fact.uiState.FactUi

sealed interface FactsUiEvent {
    object OnClickTryAgainSearchFacts : FactsUiEvent
    object OnClickSearchButton : FactsUiEvent
    data class OnClickShareFactButton(val factUi: FactUi) : FactsUiEvent
    data class OnReceiveSearch(val search: String) : FactsUiEvent
}
