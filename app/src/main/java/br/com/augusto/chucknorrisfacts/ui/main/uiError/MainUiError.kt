package br.com.augusto.chucknorrisfacts.ui.main.uiError

import br.com.augusto.chucknorrisfacts.domain.Result
import br.com.augusto.chucknorrisfacts.ui.main.uiEvent.MainUiEvent

data class MainUiError(
    val error: Result.Error,
    val tryAgainUiEvent: MainUiEvent
)
