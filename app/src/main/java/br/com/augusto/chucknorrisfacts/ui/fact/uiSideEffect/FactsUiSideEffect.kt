package br.com.augusto.chucknorrisfacts.ui.fact.uiSideEffect

import br.com.augusto.chucknorrisfacts.ui.fact.uiState.FactUi

sealed interface FactsUiSideEffect {

    sealed interface Open : FactsUiSideEffect {
        data class FactSharedDialog(val factUi: FactUi) : Open
    }
    sealed interface NavigateTo : FactsUiSideEffect {
        object SearchScreen : FactsUiSideEffect
    }
}
