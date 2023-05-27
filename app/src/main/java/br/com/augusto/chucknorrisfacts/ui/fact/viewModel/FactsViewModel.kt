package br.com.augusto.chucknorrisfacts.ui.fact.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.augusto.chucknorrisfacts.domain.Result
import br.com.augusto.chucknorrisfacts.domain.model.Fact
import br.com.augusto.chucknorrisfacts.domain.useCase.SearchFactsUseCase
import br.com.augusto.chucknorrisfacts.ui.fact.mapper.FactUiMapper
import br.com.augusto.chucknorrisfacts.ui.fact.uiError.FactsUiError
import br.com.augusto.chucknorrisfacts.ui.fact.uiEvent.FactsUiEvent
import br.com.augusto.chucknorrisfacts.ui.fact.uiSideEffect.FactsUiSideEffect
import br.com.augusto.chucknorrisfacts.ui.fact.uiState.FactUi
import br.com.augusto.chucknorrisfacts.ui.fact.uiState.FactsUiState
import br.com.augusto.chucknorrisfacts.ui.utils.SingleEventLiveData
import kotlinx.coroutines.launch

class FactsViewModel(
    private val searchFactsUseCase: SearchFactsUseCase,
    private val factUiMapper: FactUiMapper
) : ViewModel() {

    private val _uiState = MutableLiveData(FactsUiState())
    val uiState: LiveData<FactsUiState> = _uiState
    private val _uiError = SingleEventLiveData<FactsUiError>()
    val uiError: LiveData<FactsUiError> = _uiError
    private val _uiSideEffect = SingleEventLiveData<FactsUiSideEffect>()
    val uiSideEffect: LiveData<FactsUiSideEffect> = _uiSideEffect

    fun onNewUiEvent(factsUiEvent: FactsUiEvent) {
        when (factsUiEvent) {
            is FactsUiEvent.OnClickSearchButton -> handleClickSearchButton()
            is FactsUiEvent.OnClickShareFactButton -> {
                handleOnClickShareFactButton(factsUiEvent.factUi)
            }
            FactsUiEvent.OnClickTryAgainSearchFacts -> handleOnClickTryAgainSearchFacts()
            is FactsUiEvent.OnReceiveSearch -> handleOnReceiveSearch(factsUiEvent.search)
        }
    }

    private fun search() {
        _uiState.value = _uiState.value?.copy(
            showLoading = true,
        )
        viewModelScope.launch {
            when (val result = searchFactsUseCase(_uiState.value?.search ?: "")) {
                is Result.Success -> onSearchFactsSuccessfully(result.data)
                is Result.Error -> onSearchFactsFailed(result)
            }
        }
    }

    private fun onSearchFactsSuccessfully(facts: List<Fact>) {
        _uiState.value = _uiState.value?.copy(
            showLoading = false,
            facts = facts.map(factUiMapper::fromModel),
            showFactsList = facts.isNotEmpty(),
            showMessageNoFactFound = facts.isEmpty(),
        )
    }

    private fun onSearchFactsFailed(result: Result.Error) {
        _uiState.value = _uiState.value?.copy(
            showLoading = false,
        )

        _uiError.value = FactsUiError(
            error = result,
            tryAgainUiEvent = FactsUiEvent.OnClickTryAgainSearchFacts,
        )
    }

    private fun handleClickSearchButton() {
        _uiSideEffect.value = FactsUiSideEffect.NavigateTo.SearchScreen
    }

    private fun handleOnClickShareFactButton(factUi: FactUi) {
        _uiSideEffect.value = FactsUiSideEffect.Open.FactSharedDialog(factUi)
    }

    private fun handleOnClickTryAgainSearchFacts() {
        search()
    }

    private fun handleOnReceiveSearch(search: String) {
        _uiState.value = _uiState.value?.copy(
            search = search,
            showMessageToSearchFact = false,
            showMessageNoFactFound = false,
            showFactsList = true,
        )

        search()
    }
}
