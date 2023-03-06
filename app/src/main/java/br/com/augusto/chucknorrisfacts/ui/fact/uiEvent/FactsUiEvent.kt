package br.com.augusto.chucknorrisfacts.ui.fact.uiEvent

import br.com.augusto.chucknorrisfacts.ui.fact.uiState.FactUi

sealed interface FactsUiEvent {
    object OnInitScreen : FactsUiEvent
    object OnClickTryAgainSearchFacts : FactsUiEvent
    object OnClickSearchButton : FactsUiEvent
    data class OnClickToShareFact(val factUi: FactUi) : FactsUiEvent
    data class OnReceiveSearch(val search: String) : FactsUiEvent
}
