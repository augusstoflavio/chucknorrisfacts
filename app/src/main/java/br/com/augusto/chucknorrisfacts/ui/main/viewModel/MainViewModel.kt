package br.com.augusto.chucknorrisfacts.ui.main.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.augusto.chucknorrisfacts.domain.Result
import br.com.augusto.chucknorrisfacts.domain.useCase.SyncCategoriesUseCase
import br.com.augusto.chucknorrisfacts.ui.main.uiError.MainUiError
import br.com.augusto.chucknorrisfacts.ui.main.uiEvent.MainUiEvent
import br.com.augusto.chucknorrisfacts.ui.utils.SingleEventLiveData
import kotlinx.coroutines.launch

class MainViewModel(
    private val syncCategoriesUseCase: SyncCategoriesUseCase,
) : ViewModel() {

    private val _uiError = SingleEventLiveData<MainUiError>()
    val uiError: LiveData<MainUiError> = _uiError

    fun onUiEvent(mainUiEvent: MainUiEvent) {
        when (mainUiEvent) {
            MainUiEvent.OnInitScreen -> handleOnInitScreen()
            MainUiEvent.OnClickTryAgainErrorDialogButton -> handleOnClickTryAgainErrorDialogButton()
        }
    }

    private fun handleOnInitScreen() {
        syncCategories()
    }

    private fun handleOnClickTryAgainErrorDialogButton() {
        syncCategories()
    }

    private fun syncCategories() {
        viewModelScope.launch {
            when (val result = syncCategoriesUseCase()) {
                is Result.Success -> {
                    // nothing to do
                }
                is Result.Error -> onSyncCategoriesFailed(result)
            }
        }
    }

    private fun onSyncCategoriesFailed(result: Result.Error) {
        _uiError.value = MainUiError(
            error = result,
            tryAgainUiEvent = MainUiEvent.OnClickTryAgainErrorDialogButton,
        )
    }
}
