package br.com.augusto.chucknorrisfacts.ui.fact.uiError

import br.com.augusto.chucknorrisfacts.domain.Result
import br.com.augusto.chucknorrisfacts.ui.fact.uiEvent.SearchFactsUiEvent

data class SearchFactsUiError(
    val error: Result.Error,
    val tryAgainUiEvent: SearchFactsUiEvent,
)
