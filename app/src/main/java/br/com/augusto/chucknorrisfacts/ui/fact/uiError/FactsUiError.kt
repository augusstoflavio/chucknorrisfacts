package br.com.augusto.chucknorrisfacts.ui.fact.uiError

import br.com.augusto.chucknorrisfacts.domain.Result
import br.com.augusto.chucknorrisfacts.ui.fact.uiEvent.FactsUiEvent

data class FactsUiError(
    val error: Result.Error,
    val tryAgainUiEvent: FactsUiEvent
)
