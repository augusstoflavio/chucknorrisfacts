package br.com.augusto.chucknorrisfacts.ui.fact.uiSideEffect

sealed interface SearchFactsUiSideEffect {

    sealed interface Navigate : SearchFactsUiSideEffect {
        data class ToPreviousScreenWithSearch(val search: String) : Navigate
    }
}
