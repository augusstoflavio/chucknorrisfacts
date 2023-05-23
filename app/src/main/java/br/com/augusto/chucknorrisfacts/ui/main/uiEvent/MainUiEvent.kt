package br.com.augusto.chucknorrisfacts.ui.main.uiEvent

sealed interface MainUiEvent {

    object OnInitScreen : MainUiEvent
    object OnClickTryAgainErrorDialogButton : MainUiEvent
}