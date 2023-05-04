package br.com.augusto.chucknorrisfacts.ui.fact.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.augusto.chucknorrisfacts.domain.Result
import br.com.augusto.chucknorrisfacts.domain.model.Category
import br.com.augusto.chucknorrisfacts.domain.model.Search
import br.com.augusto.chucknorrisfacts.domain.useCase.GetCategoriesUseCase
import br.com.augusto.chucknorrisfacts.domain.useCase.GetLatestSearchesUseCase
import br.com.augusto.chucknorrisfacts.ui.fact.uiError.SearchFactsUiError
import br.com.augusto.chucknorrisfacts.ui.fact.uiEvent.SearchFactsUiEvent
import br.com.augusto.chucknorrisfacts.ui.fact.uiSideEffect.SearchFactsUiSideEffect
import br.com.augusto.chucknorrisfacts.ui.fact.uiState.CategoryUi
import br.com.augusto.chucknorrisfacts.ui.fact.uiState.SearchFactsUiState
import br.com.augusto.chucknorrisfacts.ui.fact.uiState.SearchUi
import br.com.augusto.chucknorrisfacts.ui.utils.SingleEventLiveData
import kotlinx.coroutines.launch

class SearchFactsViewModel(
    private val getLatestSearchesUseCase: GetLatestSearchesUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val initialUiState: SearchFactsUiState = SearchFactsUiState()
) : ViewModel() {

    private val _uiState = MutableLiveData(initialUiState)
    val uiState: LiveData<SearchFactsUiState> = _uiState
    private val _uiSideEffect = SingleEventLiveData<SearchFactsUiSideEffect>()
    val uiSideEffect: LiveData<SearchFactsUiSideEffect> = _uiSideEffect
    private val _uiError = SingleEventLiveData<SearchFactsUiError>()
    val uiError: LiveData<SearchFactsUiError> = _uiError

    private var isLoadingLastSearches = false
        private set(value) {
            field = value
            calculateShowLoading()
        }
    private var isLoadingCategories = false
        private set(value) {
            field = value
            calculateShowLoading()
        }

    private fun calculateShowLoading() {
        _uiState.value = _uiState.value?.copy(
            showLoading = isLoadingCategories || isLoadingLastSearches,
        )
    }

    fun onNewUiEvent(searchFactsUiEvent: SearchFactsUiEvent) {
        when (searchFactsUiEvent) {
            SearchFactsUiEvent.OnInitScreen -> handleOnInitScreen()
            is SearchFactsUiEvent.OnChangeSearch -> changeSearch(searchFactsUiEvent.search)
            is SearchFactsUiEvent.OnClickLastSearch -> changeSearch(searchFactsUiEvent.search.name)
            is SearchFactsUiEvent.OnClickCategory -> changeSearch(searchFactsUiEvent.category.name)
            SearchFactsUiEvent.OnClickTryAgainLoadLastSearches -> loadLastSearches()
            SearchFactsUiEvent.OnClickTryAgainLoadCategories -> loadCategories()
        }
    }

    private fun handleOnInitScreen() {
        //loadLastSearches()
        loadCategories()
    }

    private fun loadLastSearches() {
        isLoadingLastSearches = true

        viewModelScope.launch {
            when (val result = getLatestSearchesUseCase()) {
                is Result.Success -> onLoadLastSearchesSuccessfully(result.data)
                is Result.Error -> onLoadLastSearchesWithError(result)
            }

            isLoadingLastSearches = false
        }
    }

    private fun onLoadLastSearchesSuccessfully(data: List<Search>) {
        _uiState.value = _uiState.value?.copy(
            lastSearches = data.map {
                SearchUi(
                    name = it.name ?: "",
                )
            },
        )
    }

    private fun onLoadLastSearchesWithError(result: Result.Error) {
        _uiError.value = SearchFactsUiError(
            error = result,
            tryAgainUiEvent = SearchFactsUiEvent.OnClickTryAgainLoadLastSearches,
        )
    }

    private fun loadCategories() {
        isLoadingCategories = true

        viewModelScope.launch {
            when (val result = getCategoriesUseCase()) {
                is Result.Success -> onLoadCategoriesSuccessfully(result.data)
                is Result.Error -> onLoadCategoriesWithError(result)
            }

            isLoadingCategories = false
        }
    }

    private fun onLoadCategoriesSuccessfully(data: List<Category>) {
        _uiState.value = _uiState.value?.copy(
            categories = data.map {
                CategoryUi(
                    name = it.name ?: "",
                )
            },
        )
    }

    private fun onLoadCategoriesWithError(result: Result.Error) {
        _uiError.value = SearchFactsUiError(
            error = result,
            tryAgainUiEvent = SearchFactsUiEvent.OnClickTryAgainLoadCategories,
        )
    }

    private fun changeSearch(search: String) {
        _uiSideEffect.value = SearchFactsUiSideEffect.Navigate.ToPreviousScreenWithSearch(
            search = search,
        )
    }
}
